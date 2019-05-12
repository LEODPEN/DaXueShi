package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.enums.UserStatusEnums;
import com.daxueshi.sqlwork.exception.MyException;
import com.daxueshi.sqlwork.service.MailService;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.CheckcodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MailService  mailService;

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll(){
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<User> userList = (List<User>) redisTemplate.opsForValue().get("allUsers");
        //双重检测锁，避免缓存穿透
        if(userList == null){
            synchronized (this){
                 userList = (List<User>) redisTemplate.opsForValue().get("allUsers");
                 if(userList == null){
                     System.out.println("use database");
                     userList = userDao.findAll();
                     redisTemplate.opsForValue().set("allUsers",userList);
                 }
            }
        }
        return userList;
    }

    @Override
    public User login(String email,String password) {
        User user = userDao.findByMail(email);
        if (user.getStatus().equals(UserStatusEnums.TOBEVERIFIED.getCode())){
            log.warn("账号未激活,需在邮箱激活");
            return null;
        }
        if(user != null &&
                encoder.matches(password,user.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    public boolean activate(String userId,int status) {
        User user = userDao.findById(userId);
        if(user == null)
            return false;
        user.setStatus(status);
        userDao.updateUser(user);
        return true;
    }

    @Override
    public void activeByEmail(String email,String checkCode) {
        User user =userDao.findByMail(email);
        if (user == null){
            log.info("邮箱未注册");
            throw new MyException(ResultEnums.NO_SUCH_USER);
        }
        String code = (String) redisTemplate.opsForValue().get("checkcode_"+email);
        if(code == null){
            throw new MyException(ResultEnums.WAIT_TO_VERIFY);
        }
        if(!code.equals(checkCode)){
            throw new MyException(ResultEnums.WRONG_CODE);
        }
        user.setStatus(UserStatusEnums.SECRETBUTVERIFIED.getCode());
        userDao.updateUser(user);
    }

    @Override
    public boolean register(User user) {
        User u = userDao.findByMail(user.getEmail());
        if(u != null) {
            return false;
        }
        user.setStatus(UserStatusEnums.TOBEVERIFIED.getCode());
        user.setRegisterTime(new Date());
        user.setLastEditTime(new Date());
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.saveUser(user);
        log.info("到邮箱找验证码并完成注册");
        return true;
    }

    @Cacheable(value = "user",key = "#email")
    @Override
    public User findByEmail(String email) {
        return userDao.findByMail(email);
    }

    @CacheEvict(value = "user",key = "#user.email")
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @CacheEvict(value = "user",key = "#email")
    @Override
    public int deleteByEmail(String email) {
        return userDao.deleteByMail(email);
    }

    @Override
    public void sendCheckcode(String email) {
        String checkcode = CheckcodeUtils.getCheckcode();
        //过期时间设置？
        redisTemplate.opsForValue().set("checkcode_"+email,checkcode);
        String subject = "Hello"+", This is a register mail from MTD";

        String content = "your code is "+checkcode+", please complex your registration in 30 minutes.";
        mailService.sendHtmlMail(email,subject,content);
    }

    @Override
    public void save(User user) {
        userDao.saveUser(user);
    }


}
