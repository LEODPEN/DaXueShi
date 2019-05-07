package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author onion
 * @date 2019-04-13 -11:21
 * jwt工具类
 */
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    private String key ;
    private Long ttl ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public  String createJwt(User user){
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
    public Claims parseJwt(String jwt){
        try{
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
