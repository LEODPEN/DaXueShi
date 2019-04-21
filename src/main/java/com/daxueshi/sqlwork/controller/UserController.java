package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.domain.JsonData;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public JsonData login(@RequestBody User user){
        User userLogin = userService.login(user);
        if(userLogin == null){
            return new JsonData(-1,null,"登录失败");
        }
        return new JsonData(0,null,"登录成功");
    }
}
