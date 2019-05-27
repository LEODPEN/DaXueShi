package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.JobInfo;
import com.daxueshi.sqlwork.service.GraduateService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:40
 */
@RestController
@Api(tags = "毕业生信息查询相关请求")
@RequestMapping("/dxs")
public class GraduateController {
    @Autowired
    private GraduateService graduateService;

    @ApiOperation("查询指定专业毕业生信息")
    @GetMapping("/graduate/peers")
    public Result findByMajorName(@RequestParam String majorName){
        List<JobInfo> jobInfos = graduateService.findByMajorName(majorName);
        return ResultUtils.success(jobInfos);
    }

    @ApiOperation("查询指定学校指定专业毕业生信息")
    @GetMapping("/graduate/classmates")
    public Result findByUniversityAndMajor(@RequestParam String universityName,@RequestParam String majorName){
        List<JobInfo> jobInfos = graduateService.findByUniversityNameAndMajor(universityName, majorName);
        return  ResultUtils.success(jobInfos);
    }

    @ApiOperation("根据薪资等级查询毕业生信息")
    @GetMapping("/graduate/salaryLevel")
    public Result findBySalary(@RequestParam Integer salaryLevel){
        List<JobInfo> jobInfos = graduateService.findBySalaryLevel(salaryLevel);
        return ResultUtils.success(jobInfos);
    }

    @ApiOperation("插入毕业生信息")
    @PostMapping("/graduate")
    public Result save(@RequestBody Graduate graduate){
        graduateService.save(graduate);
        return ResultUtils.success();
    }

    @ApiOperation("删除毕业生信息")
    @DeleteMapping("/graduate")
    public Result delete(@RequestParam String email){
        graduateService.delete(email);
        return ResultUtils.success();
    }

    @ApiOperation("更新毕业生信息")
    @PutMapping("/graduate")
    public Result update(@RequestParam String email,@RequestBody Graduate graduate){
        graduate.setEmail(email);
        graduateService.update(graduate);
        return ResultUtils.success();
    }
}
