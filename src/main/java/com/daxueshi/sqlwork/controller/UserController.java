package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.enums.UserEnums;
import com.daxueshi.sqlwork.service.GraduateService;
import com.daxueshi.sqlwork.service.StudentService;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.GraduateJwtUtils;
import com.daxueshi.sqlwork.utils.StudentJwtUtils;
import com.daxueshi.sqlwork.utils.UserJwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "用户请求")
@RequestMapping("/dxs")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GraduateService graduateService;


    @ApiOperation("查看邮箱是否可用")
    @GetMapping("/user/checkAvailable")
    public Result isAvailable(@RequestParam String email) {
        if (userService.findByEmail(email) != null) {
            return ResultUtils.error(UserEnums.EMAIL_REGISTERED);
        } else {
            return ResultUtils.success();
        }
    }


    @ApiOperation("更新用户信息")
    @PutMapping("/user")
    public Result update(@RequestBody User user) {
        userService.updateUser(user);
        return ResultUtils.success();
    }


    @ApiOperation("删除用户信息")
    @DeleteMapping("/user/")
    public Result delete(@RequestParam String email, @RequestParam String checkCode) {
        userService.deleteByEmail(email, checkCode);
        return ResultUtils.success();
    }

    @ApiOperation("发送验证码")
    @GetMapping("/user/sendCode")
    public Result sendCheckCode(@RequestParam String email) {
        userService.sendCheckcode(email);
        return ResultUtils.success("验证码已发送");
    }

    @ApiOperation("忘记密码")
    @PostMapping("user/forgetPassword")
    public Result resetPassword(@RequestParam String checkCode, @RequestParam String email, @RequestParam String password) {
        userService.resetPassword(email, checkCode, password);
        return ResultUtils.success();
    }

    @ApiOperation("关注")
    @PutMapping("user/follow")
    public Result follow(@RequestParam String followingEmail, @RequestParam String followedEmail) {
        userService.follow(followingEmail, followedEmail);
        return ResultUtils.success();
    }

    @ApiOperation("取消关注")
    @DeleteMapping("user/cancelFollow")
    public Result cancelFollow(@RequestParam String followingEmail, @RequestParam String followedEmail) {
        userService.cancelFollow(followingEmail, followedEmail);
        return ResultUtils.success();
    }

    @ApiOperation("记录访问主页次数")
    @PostMapping("user/visit")
    public Result visit(@RequestParam String followingEmail, @RequestParam String followedEmail) {
        userService.visit(followingEmail, followedEmail);
        return ResultUtils.success();
    }


    /*******身份转换操作********/

    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    public Result register(@RequestBody User user, @RequestParam String checkcode) {
        userService.register(user, checkcode);
        return ResultUtils.success();
    }


    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result login(@RequestParam String email, @RequestParam String password) {
        Object o = userService.login(email, password);
        Map<String,String> loginInfo = new HashMap<>();
        if (o==null){
            return ResultUtils.error(UserEnums.LOGIN_FAIL);
        }
        else if (o instanceof User){
            User user = (User)o;
            String token = UserJwtUtils.createJwt(user);
            loginInfo.put("token",token);
            loginInfo.put("nickname",user.getNickname());
        }
        else if (o instanceof Student){
            Student student = (Student)o;
            String token = StudentJwtUtils.createJwt(student);
            loginInfo.put("token",token);
            loginInfo.put("nickname", userDao.findByMail(student.getEmail()).getNickname());
        }
        else {
            Graduate graduate = (Graduate)o;
            String token = GraduateJwtUtils.createJwt(graduate);
            loginInfo.put("token",token);
            loginInfo.put("nickname", userDao.findByMail(graduate.getEmail()).getNickname());
        }
        return ResultUtils.success(loginInfo);
    }

    //认证成为本科学生

    @PostMapping("/user/becomeStudent")
    //虽然可以同时使用，但是一般还是别这么干，以后有机会改
    public Result becomeStudent(@RequestBody Student student, @RequestParam String token) {

        String email = (String) UserJwtUtils.parseJwt(token).get("email");
        student.setEmail(email);
        studentService.save(student);
        log.info("{}认证成为学生", email);
        String studentToken = StudentJwtUtils.createJwt(student);
        //回传token，前端将原来的token替换掉
        return ResultUtils.success(studentToken);
    }


    //认证成为毕业生

    @PostMapping("/user/becomeGraduate")
    public Result becomeGraduate(@RequestBody Graduate graduate, @RequestParam String token) {

        String email = (String) UserJwtUtils.parseJwt(token).get("email");
        graduate.setEmail(email);
        graduateService.save(graduate);
        log.info("{}认证成为毕业生", email);
        String graduateToken = GraduateJwtUtils.createJwt(graduate);
        //回传token，前端将原来的token替换掉
        return ResultUtils.success(graduateToken);
    }
}
