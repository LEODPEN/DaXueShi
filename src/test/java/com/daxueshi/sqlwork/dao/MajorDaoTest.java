package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author onion
 * @date 2019-04-11 -16:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorDaoTest {
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private UniversityDao universityDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;
    @Test
    public void testRedis(){
        User user = userDao.findById("001");
        redisTemplate.opsForValue().set("user",user);
        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println("user1 = " + user1);

    }

    @Test
    public void testRedisLock(){
        /*ExecutorService executorService = Executors.newFixedThreadPool(25);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                userService.findAll();
            }
        });*/
        userService.findAll();
        userService.findAll();
        userService.findAll();
    }
}
