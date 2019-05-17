package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.University;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.service.UniversityService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-09 -21:21
 */
@Api(tags = "查询大学相关请求")
@RestController
@RequestMapping("/api/v1")
public class UniversityController {
    @Autowired
    private UniversityService universityService;
    @ApiOperation("查询所有大学")
    @GetMapping("/universities")
    public Result allUniversities(){
        List<University> universityList = universityService.findAll();
        if(universityList == null || universityList.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(universityList);
    }

    @ApiOperation("查询开设某专业的所有大学")
    @ApiImplicitParam(name = "majorId",value = "专业代号")
    @GetMapping("/universities/major/{majorId}")
    public Result universitiesByMajor(@PathVariable Integer majorId){
        List<University> universityList = universityService.findByMajorId(majorId);
        if(universityList == null || universityList.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(universityList);
    }

    @ApiOperation("查询某个城市拥有的大学")
    @ApiImplicitParam(name = "city",value = "城市名")
    @GetMapping("/universities/city/{city}")
    public Result universityByCity(@PathVariable String city){
        List<University> universityList = universityService.findByCity(city);
        if(universityList == null || universityList.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(universityList);
    }

}
