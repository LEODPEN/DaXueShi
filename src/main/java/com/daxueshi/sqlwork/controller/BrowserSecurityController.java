package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.utils.ResultUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author onion
 * @date 2019-05-11 -10:10
 */
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,"/login.html");
            }
        }
        return ResultUtils.withMessage("服务身份需要验证");
    }
}
