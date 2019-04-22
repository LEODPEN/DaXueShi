package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.domain.JsonData;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public JsonData login(@RequestBody Map<String,String>loginMap){
        User user = userService.login(loginMap.get("email"),loginMap.get("password"));
        if(user != null){
            String token = JwtUtils.createJwt(user);
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",user.getNickname());
            map.put("portrait",user.getPortraitUrl());
            return new JsonData(0,map,"登录成功");
        }else{
            return new JsonData(-1,null,"登录失败");
        }
    }
}
