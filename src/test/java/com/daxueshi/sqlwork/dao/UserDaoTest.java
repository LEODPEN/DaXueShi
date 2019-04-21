package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-09 -09:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testFindCount(){
        int totalUser = userDao.findTotalUser();
        System.out.println("totalUser = " + totalUser);
    }

    @Test
    public void testFindAll(){
        List<User> all = userDao.findAll();
        for (User user: all) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User user = userDao.findById("002");
        System.out.println("user = " + user);
    }
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserId("004");
        user.setPassword("987654321");
        user.setNickname("onion");
        System.out.println("effect " + userDao.saveUser(user));
    }
    @Test
    public void testFindByNickname(){
        List<User> users = userDao.findByNickName("nio");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        User user = userDao.findById("004");
        user.setNickname("strawberry");
        System.out.println(userDao.updateUser(user));
        List<User> list = userDao.findAll();
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    @Test
    public void testDelete(){
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println("user = " + user);
        }
        userDao.deleteUser("004");
        all = userDao.findAll();
        System.out.println("after delete");
        for (User user : all) {
            System.out.println("user = " + user);
        }
    }


}
