package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author onion
 * @date 2019-04-13 -11:21
 * jwt工具类
 */
public class JwtUtils {
    private final static String key = "ecnusoft";
    private final static Long ttl = 3600000L;
    public static String createJwt(User user){
        if(user == null || user.getUserId() == null)
            return null;
        long cur = System.currentTimeMillis();
        Date present = new Date(cur);
        JwtBuilder builder = Jwts.builder().setId(user.getUserId())
                .claim("name",user.getNickname())
                .claim("img",user.getPortraitUrl())
                .setIssuedAt(present)
                .signWith(SignatureAlgorithm.HS256,key);
        if(ttl > 0){
            builder.setExpiration(new Date(cur+ttl));
        }
        return builder.compact();
    }
    public static Claims parseJwt(String jwt){
        try{
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
