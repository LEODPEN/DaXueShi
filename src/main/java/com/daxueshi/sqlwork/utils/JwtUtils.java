package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author onion
 * @date 2019-04-13 -11:21
 * jwt工具类
 */
@Component
@Getter
@Setter
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    private String key ;
    private Long ttl ;

    public String createJwt(User user){
        if(user == null || user.getEmail() == null)
            return null;
        long cur = System.currentTimeMillis();
        Date present = new Date(cur);
        JwtBuilder builder = Jwts.builder()
                .claim("name",user.getNickname())
                .claim("img",user.getPortraitUrl())
                .claim("email",user.getEmail())
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
