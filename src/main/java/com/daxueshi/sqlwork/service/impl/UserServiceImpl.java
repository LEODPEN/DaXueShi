package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
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
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

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
    public boolean register(User user) {
        User u = userDao.findByMail(user.getEmail());
        if(u != null) {
            return false;
        }
        user.setStatus(0);
        user.setRegisterTime(new Date());
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.saveUser(user);
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


}
