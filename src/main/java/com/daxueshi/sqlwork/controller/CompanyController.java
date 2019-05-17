package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Company;
import com.daxueshi.sqlwork.domain.JobInfo;
import com.daxueshi.sqlwork.service.CompanyService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:22
 */
@RestController
@RequestMapping("/api/v1")
@Api(tags = "公司查询相关请求")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @ApiOperation("查询所有公司")
    @GetMapping("/companies")
    public Result findAll(){
        List<Company> companyList = companyService.findAll();
        return ResultUtils.success(companyList);
    }

    @ApiOperation("查询某一城市中的公司")
    @GetMapping("/companies/city/{city}")
    public Result findByCity(@PathVariable String city){
        List<Company> companyList = companyService.findByCity(city);
        return ResultUtils.success(companyList);
    }

    @ApiOperation("查询某类型的公司")
    @GetMapping("/companies/type/{type}")
    public Result findByType(@PathVariable String type){
        List<Company> companyList = companyService.findByType(type);
        return ResultUtils.success(companyList);
    }

    @ApiOperation("查询公司毕业生薪水情况")
    @GetMapping("/companies/{companyId}")
    public Result findSalaryCondition(@PathVariable Integer companyId){
        List<JobInfo> jobInfos = companyService.findJobInfo(companyId);
        return ResultUtils.success(jobInfos);
    }

}
