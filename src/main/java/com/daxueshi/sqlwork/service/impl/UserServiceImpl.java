package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.config.WeChatConfig;
import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    /*
    @Autowired
    private RedisClient redisClient;*/
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User saveWeChatUser(String code) {
        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(),weChatConfig.getOpenAppId(),
                weChatConfig.getOpenAppsecret());
        //获取access_token
        Map<String,Object>baseMap = HttpUtils.doGet(accessTokenUrl);
        if(baseMap == null || baseMap.isEmpty())
            return null;
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");
        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),accessToken,openId);
        Map<String,Object>baseUserMap = HttpUtils.doGet(accessTokenUrl);
        if(baseUserMap == null || baseUserMap.isEmpty())
            return null;
        String nickname = (String) baseUserMap.get("nickname");
        //Integer sex = (Integer) baseUserMap.get("sex");
        String headImgUrl = (String) baseUserMap.get("headimgurl");
        //封装对象 先查找用户是否存在
        User user = new User();
        user.setNickname(nickname);
        user.setPortraitUrl(headImgUrl);
        user.setRegisterTime(new Date());

        userDao.saveUser(user);

        return user;
    }

    /**
     * 使用redisTemplate缓存用户信息，未使用RedisClient工具类
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



}
