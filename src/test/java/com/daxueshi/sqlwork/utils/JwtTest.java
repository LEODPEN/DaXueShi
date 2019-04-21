package com.daxueshi.sqlwork.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @author onion
 * @date 2019-04-21 -09:53
 */
public class JwtTest {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("小马")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println("jwt = " + jwtBuilder.compact());
    }
    @Test
    public void testParse(){
        Claims claims = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1NTU4MTE3Mjd9.G8rZfTzTVuBLxJWlkTJkAOt-u_2fu-zxMYJIWE9dIfs")
                .getBody();
        System.out.println("用户id"+claims.getId());
        System.out.println("用户名"+claims.getSubject());
        System.out.println("登陆时间"+claims.getIssuedAt());

    }
}
