package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.University;
import com.daxueshi.sqlwork.service.UniversityService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-09 -21:21
 */
@Api(tags = "查询大学相关请求")
@RestController
@RequestMapping("/dxs")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @ApiOperation("查询某个城市拥有的大学")
    @GetMapping("/university")
    public Result universityByCity(@RequestParam String city){
        List<University> universityList = universityService.findByCity(city);
        return ResultUtils.success(universityList);
    }

}
