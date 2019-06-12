package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.dao.CompanyDao;
import com.daxueshi.sqlwork.dao.InterviewDao;
import com.daxueshi.sqlwork.domain.Company;
import com.daxueshi.sqlwork.domain.Interview;
import com.daxueshi.sqlwork.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-12 -16:25
 */
@RestController
@RequestMapping("/dxs")
public class InterviewController {
    @Autowired
    private InterviewDao interviewDao;

    @Autowired
    private CompanyDao companyDao;

    @GetMapping("/company")
    public Result getAllCompany(){
        List<Company> companyList = companyDao.findAllCompanies();
        return ResultUtils.success(companyList);
    }

    @GetMapping("/company/name")
    public Result getCompanyByName(@RequestParam String companyName){
        List<Company> companyList = companyDao.findByName(companyName);
        return ResultUtils.success(companyList);
    }

    @GetMapping("/company/city")
    public Result getCompanyByCity(@RequestParam String city){
        List<Company> companyList = companyDao.findByCity(city);
        return ResultUtils.success(companyList);
    }

    @GetMapping("/interview")
    public Result getInterview(@RequestParam String company){
        List<Interview> interviewList = interviewDao.findAllInterviews(company);
        return ResultUtils.success(interviewList);
    }

    @PostMapping("/interview")
    public Result addInterview(@RequestBody Interview interview){
        interviewDao.addInterview(interview);
        return ResultUtils.success();
    }

}
