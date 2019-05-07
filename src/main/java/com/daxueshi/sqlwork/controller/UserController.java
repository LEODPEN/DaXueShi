package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
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

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String>loginMap){
        User user = userService.login(loginMap.get("email"),loginMap.get("password"));
        if(user != null){
            String token = jwtUtils.createJwt(user);
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",user.getNickname());
            map.put("portrait",user.getPortraitUrl());
            return  ResultUtils.success(map);
        }else{
            return ResultUtils.error(ResultEnums.LOGIN_ERROR);
        }
    }


}
