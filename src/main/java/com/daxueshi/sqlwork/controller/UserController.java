package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.UserEnums;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户请求")
@RestController
@RequestMapping("/dxs")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result login(@RequestParam String email,@RequestParam String password){
        User user = userService.login(email, password);
        if(user != null){
            String token = JwtUtils.createJwt(user);
            return ResultUtils.success(token);
        }
        return ResultUtils.error(UserEnums.LOGIN_FAIL);
    }

    @ApiOperation("查看邮箱是否可用")
    @GetMapping("/user/checkAvailable")
    public Result isAvailable(@RequestParam String email){
        if(userService.findByEmail(email)!= null){
            return ResultUtils.error(UserEnums.EMAIL_REGISTERED);
        }else{
            return ResultUtils.success();
        }
    }

    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    public Result register(@RequestBody User user,@RequestParam String checkcode){
        userService.register(user,checkcode);
        return ResultUtils.success();
    }


    @ApiOperation("更新用户信息")
    @PutMapping("/user")
    public Result update(@RequestBody User user){
        userService.updateUser(user);
        return ResultUtils.success();
    }


    @ApiOperation("删除用户信息")
    @DeleteMapping("/user/")
    public Result delete(@RequestParam String email,@RequestParam String checkCode){
        userService.deleteByEmail(email,checkCode);
        return ResultUtils.success();
    }

    @ApiOperation("发送验证码")
    @GetMapping("/user/sendCode")
    public Result sendCheckCode(@RequestParam String email){
        userService.sendCheckcode(email);
        return ResultUtils.success("验证码已发送");
    }

    @ApiOperation("忘记密码")
    @PostMapping("user/forgetPassword")
    public Result resetPassword(@RequestParam String checkCode,@RequestParam String email,@RequestParam String password){
        userService.resetPassword(email,checkCode,password);
        return ResultUtils.success();
    }

    @ApiOperation("关注")
    @PutMapping("user/follow")
    public Result follow(@RequestParam String followingEmail,@RequestParam String followedEmail){
        userService.follow(followingEmail,followedEmail);
        return ResultUtils.success();
    }

    @ApiOperation("取消关注")
    @DeleteMapping("user/cancelFollow")
    public Result cancelFollow(@RequestParam String followingEmail,@RequestParam String followedEmail){
        userService.cancelFollow(followingEmail,followedEmail);
        return ResultUtils.success();
    }

    @ApiOperation("记录访问主页次数")
    @PostMapping("user/visit")
    public Result visit(@RequestParam String followingEmail,@RequestParam String followedEmail){
        userService.visit(followingEmail,followedEmail);
        return ResultUtils.success();
    }
}
