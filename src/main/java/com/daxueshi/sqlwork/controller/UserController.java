package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户请求")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("用户登录")
    @PostMapping("/users")
    public Result login(@RequestBody User user){
        userService.login(user.getEmail(),user.getPassword());
        return ResultUtils.success();
        /*if(user != null){
            String token = jwtUtils.createJwt(user);
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",user.getNickname());
            map.put("portrait",user.getPortraitUrl());
            return  ResultUtils.success(map);
        }else{
            return ResultUtils.error(ResultEnums.LOGIN_ERROR);
        }*/
    }

    @ApiOperation("用户注册")
    @PostMapping("/users/{checkcode}")
    public Result register(@RequestBody User user,@PathVariable String checkcode){
        userService.register(user,checkcode);
        return ResultUtils.success();
    }
    @ApiOperation("更新用户信息")
    @PutMapping("/users")
    public Result update(@RequestBody User user){
        userService.updateUser(user);
        return ResultUtils.success();
    }


    @ApiOperation("删除用户信息")
    @DeleteMapping("/users/{email}")
    public Result delete(@PathVariable String email){
        userService.deleteByEmail(email);
        //下一步是发验证码
        return ResultUtils.success();
    }

    //前端保存email，此处只传email即可
    @GetMapping("/users/checkcode")
    public Result sendCheckCode(@RequestParam String email){
        userService.sendCheckcode(email);
        return ResultUtils.success("请填写发送到邮箱的验证码");
    }
    /*
    @GetMapping("/users/checkCode")
    public Result checkCode(@RequestParam("email") String email,
                            @RequestParam("checkCode") String checkCode){
        userService.activeByEmail(email,checkCode);
        return ResultUtils.success();
    }*/

}
