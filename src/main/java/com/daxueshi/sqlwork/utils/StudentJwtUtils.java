package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.OtherErrorEnums;
import com.daxueshi.sqlwork.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@Slf4j
public class StudentJwtUtils {

    private final static String key = "ECNUSOFT";
    private final static Long ttl = 1000 * 60 * 60 * 24L;

    public static String createJwt(Student student){
        if(student == null || student.getEmail() == null)
            return null;
        long cur = System.currentTimeMillis();
        Date present = new Date(cur);
        JwtBuilder builder = Jwts.builder()

                .claim("majorName",student.getMajorName())
                .claim("universityName",student.getUniversityName())
                .claim("email",student.getEmail())
                .claim("grade",student.getGrade())

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
