package com.daxueshi.sqlwork.interceptor;

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


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("经过了拦截器");
//        final String loginHeader = request.getHeader("Login");
//        if(loginHeader != null && loginHeader.startsWith("token_")){
//            final String token = loginHeader.substring(6);
//            Claims claims = JwtUtils.parseJwt(token);
//            if(claims != null) {
//                System.out.println("通过了拦截器");
//                return true;
//            }
//            else{
//                System.out.println("伪造的token");
//                return false;
//            }
//        }else{
//            response.sendRedirect("/dxs/user/login");
//            System.out.println("请先登录");
//            return false;
//        }
        return true;
    }
}
