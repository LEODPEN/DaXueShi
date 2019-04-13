package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.service.MajorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RestController
@RequestMapping("")
public class PageController {
    @Autowired
    private MajorService majorService;

    @GetMapping("")
    public Object pageVideo(@RequestParam(value = "page", defaultValue = "1")int page,
                            @RequestParam(value = "size", defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<Major> list = majorService.findAll();
        PageInfo<Major> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
