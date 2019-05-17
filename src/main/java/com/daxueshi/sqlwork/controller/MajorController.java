package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.JobInfo;
import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.service.MajorService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-13 -10:01

*/
@Api(tags = "专业查询相关请求")
@RestController
@RequestMapping("/api/v1")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @ApiOperation("查询全部专业")
    @GetMapping("/majors")
    public Result allMajors(@RequestParam(value = "page", defaultValue = "1")int page,
                            @RequestParam(value = "size", defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<Major> list = majorService.findAll();
        PageInfo pageInfo = new PageInfo(list,5);
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation("查询指定大学开设的专业")
    @ApiImplicitParam(name = "univeristyId",value = "大学Id")
    @GetMapping("/majors/{universityId}")
    public Result certainMajors(@PathVariable Integer universityId){
        List<Major> list = majorService.findByUniversityId(universityId);
        if(list == null || list.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(list);
    }

    @ApiOperation("查询专业就业情况")
    @ApiImplicitParam()
    @GetMapping("/majors/jobInfo/{majorId}")
    public Result findJobInfo(@PathVariable Integer majorId){
        List<JobInfo> jobInfos = majorService.findJobInfo(majorId);
        return ResultUtils.success(jobInfos);
    }
}
