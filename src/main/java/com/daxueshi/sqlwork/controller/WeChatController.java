package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.config.WeChatConfig;
import com.daxueshi.sqlwork.domain.JsonData;
import com.daxueshi.sqlwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author onion
 * @date 2019-04-15 -14:54
 */
@Controller
@RequestMapping("/api/v1/wechat")
public class WeChatController {
    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private UserService userService;
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value="access_page",required = true)String accessPage) throws UnsupportedEncodingException {
        String redirectUrl = weChatConfig.getOpenRedirectUrl();
        String callbackUrl = URLEncoder.encode(redirectUrl,"utf-8");
        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),
                weChatConfig.getOpenAppId(),callbackUrl,accessPage);
        return JsonData.buildSuccess(qrcodeUrl);
    }

    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true)String code,
                                   String state,HttpServletResponse response){
        userService.saveWeChatUser(code);
        System.out.println("code:"+code);
        System.out.println("state:"+state);
    }
}
