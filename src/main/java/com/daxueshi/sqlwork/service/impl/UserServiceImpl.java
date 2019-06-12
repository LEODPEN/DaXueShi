package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.FollowDao;
import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.UserEnums;
import com.daxueshi.sqlwork.enums.UserStatusEnums;
import com.daxueshi.sqlwork.exception.MyException;
import com.daxueshi.sqlwork.lock.RedisLock;
import com.daxueshi.sqlwork.service.MailService;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.CheckcodeUtils;
import com.daxueshi.sqlwork.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private FollowDao followDao;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MailService  mailService;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GraduateDao graduateDao;

    private final static int TIMEOUT = 10 * 1000;

    @Override
    public Map<String,String> login(String email, String password) {
        User user = userDao.findByMail(email);
        Map loginInfo = new HashMap();
        if(user != null && encoder.matches(password,user.getPassword())) {
            log.info("账号:" + email + "已经成功登录");
            String token = JwtUtils.createJwt(user);
            loginInfo.put("token",token);
            loginInfo.put("nickname",user.getNickname());
            loginInfo.put("email",user.getEmail());
            Graduate graduate = graduateDao.findOne(email);
            if (graduate!=null){
                loginInfo.put("majorName", graduate.getMajorName());
                loginInfo.put("universityName", graduate.getUniversityName());
                loginInfo.put("role","graduate");
                return loginInfo;
            }
            Student student = studentDao.findOne(email);
            if (student!=null){
                loginInfo.put("majorName",student.getMajorName());
                loginInfo.put("universityName",student.getUniversityName());
                loginInfo.put("role","student");
                return loginInfo;
            }
        }else{
            throw new MyException(UserEnums.LOGIN_FAIL);
        }
        return loginInfo;
    }

    @Override
    public void register(User user,String checkcode) {
        String code = (String) redisTemplate.opsForValue().get("checkcode_"+user.getEmail());
        if(code == null){
            throw new MyException(UserEnums.INVALID_CODE);
        }
        if(!checkcode.equals(code)){
            throw new MyException(UserEnums.WRONG_CODE);
        }
        user.setRegisterTime(new Date());
        user.setLastLoginTime(new Date());
        user.setStatus(UserStatusEnums.VISITOR.getCode());
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.saveUser(user);
        log.info("邮箱:"+user.getEmail()+"已经被注册");
    }
    //问题
    @Override
    public User findByEmail(String email) {
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLock.lock(email, String.valueOf(time))){
            throw new MyException(UserEnums.SYNCHRONIZE_EMAIL);
        }
        redisLock.unlock(email,String.valueOf(time));
        return userDao.findByMail(email);
    }

    @Override
    public void updateUser(User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
        log.info("用户:" + user.getEmail() + "信息修改");
        userDao.updateUser(user);
    }

    @Override
    public void sendCheckcode(String email) {
        String checkcode = CheckcodeUtils.getCheckcode();
        redisTemplate.opsForValue().set("checkcode_"+email,checkcode,5*60, TimeUnit.SECONDS);
        String subject = "Hello"+", This is a register mail from DXS";
        String content = "your code is "+checkcode+", please complete your registration in 5 minutes.";
        mailService.sendHtmlMail(email,subject,content);
        log.info("向用户:" + email + "发送了验证码");
    }

    @Override
    public void resetPassword(String email, String checkCode, String password) {
        String code = (String) redisTemplate.opsForValue().get("checkcode_"+email);
        if(code == null){
            throw new MyException(UserEnums.INVALID_CODE);
        }
        if(!code.equals(checkCode)){
            throw new MyException(UserEnums.WRONG_CODE);
        }
        User user = userDao.findByMail(email);
        user.setPassword(encoder.encode(password));
        log.info("用户:" + email + "重置了密码");
        userDao.updateUser(user);
    }

    @Override
    public void deleteByEmail(String email, String checkCode) {
        String code = (String) redisTemplate.opsForValue().get("checkcode_"+email);
        if(code == null){
            throw new MyException(UserEnums.INVALID_CODE);
        }
        if(!code.equals(checkCode)){
            throw new MyException(UserEnums.WRONG_CODE);
        }
        userDao.deleteByMail(email);
    }

    @Override
    public void follow(String followingEmail, String followedEmail) {
        followDao.addFollow(followingEmail,followedEmail);
    }

    @Override
    public void cancelFollow(String followingEmail, String followedEmail) {
        followDao.cancelFollow(followingEmail,followedEmail);
    }

    @Override
    public void visit(String followingEmail, String followedEmail) {
        followDao.recordTimes(followingEmail,followedEmail);
    }
}
