package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.OtherErrorEnums;
import com.daxueshi.sqlwork.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JwtUtils {
    private final static String key = "ECNUSOFT";
    private final static Long ttl = 1000 * 60 * 60 * 24L;

    public static String createJwt(User user){
        if(user == null || user.getEmail() == null)
            return null;
        long cur = System.currentTimeMillis();
        Date present = new Date(cur);
        JwtBuilder builder = Jwts.builder()
                //就只能存user信息，因为不确定是否状态为毕业
                .claim("name",user.getNickname())
                .claim("img",user.getProfile())
                .claim("email",user.getEmail())
                .setIssuedAt(present)
                .signWith(SignatureAlgorithm.HS256,key);
        if(ttl > 0){
            builder.setExpiration(new Date(cur+ttl));
        }
        return builder.compact();
    }
    public static Claims parseJwt(String jwt){
        try{
            return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        }catch (Exception e){
            log.error("token无效或者已经过期");
            throw new MyException(OtherErrorEnums.TOKEN_ERROR);
        }
    }
}
