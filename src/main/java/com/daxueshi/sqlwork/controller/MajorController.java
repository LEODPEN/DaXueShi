package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.service.MajorService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-13 -10:01

*/
@RestController
@RequestMapping("/api/v1")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping("/majors")
    public Result allMajors(@RequestParam(value = "page", defaultValue = "1")int page,
                            @RequestParam(value = "size", defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<Major> list = majorService.findAll();
        PageInfo pageInfo = new PageInfo(list,5);
        return ResultUtils.success(pageInfo);
    }

    @GetMapping("/majors/{universityId}")
    public Result certainMajors(@PathVariable Integer universityId){
        List<Major> list = majorService.findByUniversityId(universityId);
        if(list == null || list.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(list);
    }
}
