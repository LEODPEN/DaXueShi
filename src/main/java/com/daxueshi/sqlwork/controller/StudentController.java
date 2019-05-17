package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.service.StudentService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:08
 */
@RestController
@Api(tags = "学生相关信息查询请求")
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation("查询指定学校在校生信息")
    @ApiImplicitParam(name = "universityId",value = "大学编号")
    @GetMapping("/students/university/{universityId}")
    public Result findByUniversityId(@PathVariable Integer universityId){
        List<Student> students = studentService.findByUniversityId(universityId);
        return ResultUtils.success(students);
    }

    @ApiOperation("查询指定专业在校生信息")
    @GetMapping("/students/major/{majorId}")
    @ApiImplicitParam(name = "majorId",value = "专业编号")
    public Result findByMajorId(@PathVariable Integer majorId){
        List<Student> students = studentService.findByMajorId(majorId);
        return ResultUtils.success(students);
    }

    @ApiOperation("插入学生信息")
    @PostMapping("/students/{userId}")
    public Result save(@RequestBody Student student,@PathVariable String userId){
        studentService.save(student,userId);
        return ResultUtils.success();
    }

    @ApiOperation("删除学生信息")
    @DeleteMapping("/students/{userId}")
    public Result delete(@PathVariable String userId){
        studentService.delete(userId);
        return ResultUtils.success();
    }

    @ApiOperation("更新学生信息")
    @PutMapping("/students/{userId}")
    public Result update(@PathVariable String userId,@RequestBody Student student){
        studentService.update(userId,student);
        return ResultUtils.success();
    }
}
