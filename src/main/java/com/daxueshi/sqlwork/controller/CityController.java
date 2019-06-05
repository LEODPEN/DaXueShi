package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.City;
import com.daxueshi.sqlwork.service.CityService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-05 -23:29
 */
@RestController
@RequestMapping("/dxs/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public Result findCity(@RequestParam Integer provinceId){
        List<City> cityList = cityService.findByProvinceId(provinceId);
        return ResultUtils.success(cityList);
    }

}
