package com.daxueshi.sqlwork.interceptor;

import com.daxueshi.sqlwork.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author onion
 * @date 2019-04-21 -11:24
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        final String loginHeader = request.getHeader("Login");
        if(loginHeader != null){
            Claims claims = jwtUtils.parseJwt(loginHeader);
            if(claims != null){
                request.setAttribute("user_claims",claims);
            }
        }
        return true;
    }
}
