package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.converter.TotalUserDTOConverter;
import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.dto.TotalUserDTO;
import com.daxueshi.sqlwork.enums.*;
import com.daxueshi.sqlwork.exception.MyException;
import com.daxueshi.sqlwork.service.GraduateService;
import com.daxueshi.sqlwork.service.StudentService;
import com.daxueshi.sqlwork.service.UserService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GraduateDao graduateDao;

    @ApiOperation("查看个人信息")
    @GetMapping("/user")
    public Result personalInfo(@RequestParam String email){
        User user = userDao.findByMail(email);
        //user 既然这里能查那就不会为空了
        String nickname = user.getNickname();
        TotalUserDTO t ;
        if (user.getStatus().equals(UserStatusEnums.VISITOR.getCode())){
            log.info("查看{}未认证用户信息",email);
            t=TotalUserDTOConverter.convert(user,nickname);
        }
        else if (user.getStatus().equals(UserStatusEnums.STUDENT.getCode())){
            log.info("查看{}学生信息",email);
            t=TotalUserDTOConverter.convert(studentDao.findOne(email),nickname);
        }
        else{
            log.info("查看{}毕业生信息",email);
            t=TotalUserDTOConverter.convert(graduateDao.findOne(email),nickname);
        }
        return ResultUtils.success(t);
    }


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


    @PostMapping("/user/register")
    @Transactional
    public Result register(@RequestBody User user, @RequestParam String checkcode) {
        userService.register(user, checkcode);
        return ResultUtils.success();
    }


    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result login(@RequestParam String email, @RequestParam String password) {
        Map<String,String> loginInfo = userService.login(email, password);
        return ResultUtils.success(loginInfo);
    }

    //认证成为本科学生

    @PostMapping("/user/becomeStudent")
    @Transactional
    //虽然可以同时使用，但是一般还是别这么干，以后有机会改
    public Result becomeStudent(@RequestBody Student student,@RequestParam String token) {

        String email = (String) JwtUtils.parseJwt(token).get("email");
        if (student == null){
            throw new MyException(OtherErrorEnums.NO_INPUT);
        }
        User user = userService.findByEmail(email);
        if (!user.getStatus().equals(UserStatusEnums.STUDENT.getCode())){
            user.setStatus(UserStatusEnums.STUDENT.getCode());
            userDao.updateUser(user);
            student.setEmail(email);
            studentService.save(student);
        }
        else {
            //原来就是在校生
            Student s = studentDao.findOne(email);
            s.setGrade(student.getGrade());
            s.setMajorName(student.getMajorName());
            s.setUniversityName(student.getUniversityName());
            studentDao.update(s);
        }
        log.info("{}认证成为学生", email);
//        String studentToken = StudentJwtUtils.createJwt(student);
        Map<String,Object> map = new HashMap<>();
        map.put("majorName",student.getMajorName());
        map.put("universityName",student.getUniversityName());
        //回传token，前端将原来的token替换掉
        return ResultUtils.success(map);
    }


    //认证成为毕业生

    @PostMapping("/user/becomeGraduate")
    @Transactional
    public Result becomeGraduate(@RequestBody Graduate graduate, @RequestParam String token) {

        String email = (String) JwtUtils.parseJwt(token).get("email");
        if (graduate == null){
            throw new MyException(OtherErrorEnums.NO_INPUT);
        }
        User user = userService.findByEmail(email);
        Student student = new Student();
        //本来不是毕业生
        if (!user.getStatus().equals(UserStatusEnums.GRADUATE.getCode())){
            user.setStatus(UserStatusEnums.GRADUATE.getCode());
            //原来未认证
            if (!user.getStatus().equals(UserStatusEnums.STUDENT.getCode())){

                student.setEmail(email);
                student.setUniversityName(graduate.getUniversityName());
                student.setMajorName(graduate.getMajorName());
                student.setGrade(null);
                graduate.setEmail(email);
                studentDao.save(student);
            }else {
                //原来认证为在校生
                student = studentDao.findOne(email);
                student.setGrade(null);
                studentDao.update(student);
            }
            userDao.updateUser(user);
            graduate.setEmail(email);
            graduateService.save(graduate);
        }else {
            //原来就是毕业生
            Graduate g = graduateDao.findOne(email);
            //原来的剔除
            BeanUtils.copyProperties(graduate,g);
            if (!g.getState().equals(GraduationEnums.WORK.getCode())){
                g.setSalary(SalaryEnums.O.getLevel());
            }
            g.setEmail(email);
            graduateDao.update(g);
        }
        log.info("{}认证成为毕业生", email);
//        String graduateToken = GraduateJwtUtils.createJwt(graduate);
        Map<String,Object> map = new HashMap<>();
        map.put("majorName",graduate.getMajorName());
        map.put("universityName",graduate.getUniversityName());
        //回传token，前端将原来的token替换掉
        return ResultUtils.success(map);
    }
}
