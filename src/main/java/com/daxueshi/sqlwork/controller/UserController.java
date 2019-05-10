package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户请求")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("用户登录")
    @PostMapping("/users/{email}/{password}")
    public Result login(@PathVariable String email,@PathVariable String password){
        User user = userService.login(email,password);
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

    @ApiOperation("用户注册")
    @PostMapping("/users")
    public Result register(@RequestBody User user){
        userService.register(user,null);
        return ResultUtils.success();
    }
    @ApiOperation("更新用户信息")
    @PutMapping("/users")
    public Result update(@RequestBody User user){
        userService.updateUser(user);
        return ResultUtils.success();
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/user/{email}")
    public Result delete(@PathVariable String email){
        userService.deleteByEmail(email);
        return ResultUtils.success();
    }


}
