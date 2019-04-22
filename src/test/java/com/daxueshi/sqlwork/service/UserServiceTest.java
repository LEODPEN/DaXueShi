package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author onion
 * @date 2019-04-21 -08:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testPassword(){
        User user = new User();
        user.setUserId("007");
        user.setEmail("969023015@qq.com");
        user.setPassword("10175101226");
        System.out.println(userService.register(user));
    }
    @Test
    public void testLogin(){
        String email = "969023015@qq.com";
        String password = "10175101227";
        System.out.println(userService.login(email,password));
    }
}
