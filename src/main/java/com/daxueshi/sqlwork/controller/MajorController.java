package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.service.MajorService;
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
 * @date 2019-04-13 -10:01

*/
@Api(tags = "专业查询相关请求")
@RestController
@RequestMapping("/dxs/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @ApiOperation("查询指定大学开设的专业")
    @GetMapping
    public Result certainMajors(@RequestParam String universityName){
        List<Major> list = majorService.findByUniversityName(universityName);
        return ResultUtils.success(list);
    }

    @ApiOperation("查询某专业具体信息")
    @GetMapping("/info")
    public Result majorInfo(@RequestParam String majorName){
        Major major = majorService.findMajorInfo(majorName);
        return ResultUtils.success(major);
    }

}
