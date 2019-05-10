package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.service.GraduateService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/api/v1")
public class GraduateController {
    @Autowired
    private GraduateService graduateService;
    @ApiOperation("查询指定学校毕业生信息")
    @ApiImplicitParam(name = "universityId",value = "大学编号")
    @GetMapping("/graduates/university/{universityId}")
    public Result findByUniversityId(@PathVariable Integer universityId){
        List<Graduate> graduates = graduateService.findByUniversityId(universityId);
        return ResultUtils.success(graduates);
    }
    // 参数Ambiguous
    @ApiOperation("查询指定公司毕业生信息")
    @GetMapping("/graduates/company/{companyId}")
    @ApiImplicitParam(name = "companyId",value = "公司编号")
    public Result findByCompanyId(@PathVariable Integer companyId){
        List<Graduate> graduates = graduateService.findByCompanyId(companyId);
        return ResultUtils.success(graduates);
    }

    @ApiOperation("查询指定专业毕业生信息")
    @GetMapping("/graduates/major/{majorId}")
    @ApiImplicitParam(name = "majorId",value = "专业编号")
    public Result findByMajorId(@PathVariable Integer majorId){
        List<Graduate> graduates = graduateService.findByMajorId(majorId);
        return ResultUtils.success(graduates);
    }

    @ApiOperation("根据薪资查询毕业生信息")
    @GetMapping("/graduates/salary/{salaryMin}/{salaryMax}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salaryMin",value = "最低薪资"),
            @ApiImplicitParam(name = "salaryMax",value = "最高薪资")})
    public Result findBySalary(@PathVariable Double salaryMin,@PathVariable Double salaryMax){
        List<Graduate> graduates = graduateService.findBySalary(salaryMin,salaryMax);
        return ResultUtils.success(graduates);
    }

    @ApiOperation("插入毕业生信息")
    @PostMapping("/graduates")
    public Result save(@RequestBody Graduate graduate){
        graduateService.save(graduate);
        return ResultUtils.success();
    }

    @ApiOperation("删除毕业生信息")
    @DeleteMapping("/graduates/{userId}")
    public Result delete(@PathVariable String userId){
        graduateService.delete(userId);
        return ResultUtils.success();
    }

    @ApiOperation("更新毕业生信息")
    @PutMapping("/graduates/{userId}")
    public Result update(@PathVariable String userId,@RequestBody Graduate graduate){
        graduateService.update(userId,graduate);
        return ResultUtils.success();
    }
}
