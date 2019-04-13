package com.daxueshi.sqlwork.util;

import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @author onion
 * @date 2019-04-13 -11:31
 */
public class JwtTest {
    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setUserId("005");
        user.setNickname("banana");
        user.setPortraitUrl("www.xdclass.net");
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println("token = " + token);
    }

    @Test
    public void testCheck(){
        String token = "1eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjAwNSIsIm5hbWUiOiJiYW5hbmEiLCJpbWciOiJ3d3cueGRjbGFzcy5uZXQiLCJpYXQiOjE1NTUxMjY0NDEsImV4cCI6MTU1NTczMTI0MX0.xPnXOzV5Vp-uunA04MBHOg0hQvuvE2criqt0AmtkzHM";
        Claims claims = JwtUtils.checkJWT(token);
        if(claims != null){
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            String id = (String) claims.get("id");
            System.out.println("id = " + id);
            System.out.println("img = " + img);
            System.out.println("name = " + name);
        }else{
            System.out.println("wrong answer");
        }
    }
}
