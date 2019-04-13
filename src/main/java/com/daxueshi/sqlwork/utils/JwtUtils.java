package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author onion
 * @date 2019-04-13 -11:21
 * jwt工具类
 */

public class JwtUtils {
    public static final String SUBJECT = "";
    public static final Long EXPIRE = 1000*60*60*24*7L;
    public static final String APPSECRET = "onion";
    public static String geneJsonWebToken(User user){
        if(user == null || user.getUserId()== null
                        || user.getNickname() == null || user.getPortraitUrl() == null)
            return null;
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getUserId())
                .claim("name", user.getNickname())
                .claim("img", user.getPortraitUrl())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;
    }
    public static Claims checkJWT(String token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){

        }
        return null;
    }
}
