package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.domain.University;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void testMajor(){
        List<Major> all = majorDao.findAll();
        for (Major major : all) {
            System.out.println("majorname = " + major.getMajorName());
            List<University> universityList = major.getUniversityList();
            for(University university : universityList){
                System.out.println("major in school "+university);
            }
            System.out.println("-------");
        }
    }

    @Test
    public void testUniverisity(){
        List<University> all = universityDao.findAll();
        for (University university : all) {
            System.out.println("university = " + university.getUniversityName());
            List<Major> majorList = university.getMajorList();
            for (Major major : majorList) {
                System.out.println("school has majors "+major.getMajorName());
            }
            System.out.println("-----");
        }
    }

    @Test
    public void testRedisLock(){
        ExecutorService executorService = Executors.newFixedThreadPool(25);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                userService.findAll();
            }
        });
    }
}
