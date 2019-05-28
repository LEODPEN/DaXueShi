package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.RequestDataForm.RequestForm;
import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.service.DataDisplayService;
import com.daxueshi.sqlwork.service.StudentService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:08
 */
@RestController
@Api(tags = "学生相关信息查询请求")
@RequestMapping("/dxs")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private DataDisplayService dataDisplayService;

    @ApiOperation("查询本校本专业在校生信息")
    @GetMapping("/student/classmates")
    public Result findClassmats(@RequestParam String universityName, @RequestParam String majorName){
        List<Student> students = studentService.findByUniversityAndMajor(universityName,majorName);
        return ResultUtils.success(students);
    }
    @ApiOperation("查询本专业在校生信息")
    @GetMapping("/student/peers/")
    public Result findPeers(@RequestParam String majorName){
        List<Student> students = studentService.findByMajorName(majorName);
        return ResultUtils.success(students);
    }

    @ApiOperation("插入学生信息")
    @PostMapping("/students")
    public Result save(@RequestBody Student student,@RequestParam String email){
        student.setEmail(email);
        studentService.save(student);
        return ResultUtils.success();
    }

    @ApiOperation("删除学生信息")
    @DeleteMapping("/students")
    public Result delete(@RequestParam  String email){
        studentService.delete(email);
        return ResultUtils.success();
    }

    @ApiOperation("更新学生信息")
    @PutMapping("/students")
    public Result update(@RequestParam String email,@RequestBody Student student){
        student.setEmail(email);
        studentService.update(student);
        return ResultUtils.success();
    }

    @PostMapping("/student/contiOrWork")
    public Result contiOrWork(@RequestBody RequestForm requestForm){
        Claims claims = JwtUtils.parseJwt(requestForm.getToken());
        String email = (String) claims.get("email");
        log.info("用户{}查找应届生去向分布",email);
        return ResultUtils.success(dataDisplayService.getDesCity(requestForm.getYear(),requestForm.getCollege(),requestForm.getMajor()));
    }
}
