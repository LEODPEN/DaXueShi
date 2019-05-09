package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.University;
import com.daxueshi.sqlwork.enums.ResultEnums;
import com.daxueshi.sqlwork.service.UniversityService;
import com.daxueshi.sqlwork.utils.ResultUtils;
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
@RestController
@RequestMapping("/api/v1")
public class UniversityController {
    @Autowired
    private UniversityService universityService;
    @GetMapping("/universities")
    public Result allUniversities(){
        List<University> universityList = universityService.findAll();
        if(universityList == null || universityList.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(universityList);
    }

    @GetMapping("/universities/{majorId}")
    public Result universitiesByMajor(@PathVariable Integer majorId){
        List<University> universityList = universityService.findByMajorId(majorId);
        if(universityList == null || universityList.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(universityList);
    }

    @GetMapping("/university/{city}")
    public Result universityByCity(@PathVariable String city){
        List<University> universityList = universityService.findByCity(city);
        if(universityList == null || universityList.isEmpty()){
            return ResultUtils.error(ResultEnums.INFO_NOT_EXIST);
        }
        return ResultUtils.success(universityList);
    }

}
