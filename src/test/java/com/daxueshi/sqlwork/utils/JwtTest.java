package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author onion
 * @date 2019-05-07 -09:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDao userDao;
    @Test
    public void testJwt(){
        User user = userDao.findById("001");
        String jwt = jwtUtils.createJwt(user);
        System.out.println(jwt);

    }
}
