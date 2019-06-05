package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.GraduateInfo;
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

    //查某专业毕业的所有学生信息
    @GetMapping("/graduate/peers")
    public Result findAllByMajorName(@RequestParam String majorName){
        List<Graduate> graduates = graduateService.findByMajorName(majorName);
        return ResultUtils.success(graduates);
    }

    @ApiOperation("查询指定学校指定专业毕业生信息")
    @GetMapping("/graduate/byUAM")
    public Result findByUniversityAndMajor(@RequestParam String universityName,@RequestParam String majorName){
        List<Graduate> graduates = graduateService.findByUniversityNameAndMajor(universityName, majorName);
        return  ResultUtils.success(graduates);
    }

    @ApiOperation("根据薪资等级查询毕业生信息")
    @GetMapping("/graduate/bySalaryLevel")
    public Result findBySalary(@RequestParam Integer salaryLevel){
        //Graduate里面也有salary，必要性极低
        List<GraduateInfo> graduates = graduateService.findBySalaryLevel(salaryLevel);
        return ResultUtils.success(graduates);
    }

    @ApiOperation("插入毕业生信息")
    @PostMapping("/graduate/")
    public Result save(@RequestBody Graduate graduate){
        graduateService.save(graduate);
        return ResultUtils.success();
    }

//    @ApiOperation("删除毕业生信息")
////    @DeleteMapping("/graduate")
////    public Result delete(@RequestParam String email){
////        graduateService.delete(email);
////        return ResultUtils.success();
////    }

    @ApiOperation("更新毕业生信息")
    @PutMapping("/graduate")
    public Result update(@RequestParam String email,@RequestBody Graduate graduate){
        graduate.setEmail(email);
        graduateService.update(graduate);
        return ResultUtils.success();
    }
}
