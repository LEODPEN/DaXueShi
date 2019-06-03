package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.GraduateInfo;
import com.daxueshi.sqlwork.service.GraduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:39
 */
@Service
public class GraduateServiceImpl implements GraduateService {
    @Autowired
    private GraduateDao graduateDao;


    @Override
    public void save(Graduate graduate) {
        graduateDao.save(graduate);
    }

    @Override
    public void delete(String email) {
        graduateDao.delete(email);
    }

    @Override
    public List<GraduateInfo> findByMajorName(String majorName) {
        return graduateDao.findByMajorName(majorName);
    }

    @Override
    public List<Graduate> findByUniversityNameAndMajor(String universityName, String majorName) {
        return graduateDao.findAllByUniversityAndMajor(universityName, majorName);
    }

    @Override
    public List<GraduateInfo> findGraduateJobInfoByUnameANdMname(String universityName, String majorName) {
        return graduateDao.findAllGraduateInfoByUniversityAndMajor(universityName, majorName);
    }

    @Override
    public List<Graduate> findByUniversityNameAndMajorAndYear(String universityName, String majorName, Integer year) {
        return graduateDao.findByUniversityAndMajorAndYear(universityName,majorName,year);
    }

    @Override
    public List<GraduateInfo> findGraduateJobInfoByUnameANdMnameAndYear(String universityName, String majorName, Integer year) {
        return graduateDao.findGraduateInfoByUniversityAndMajorAndYear(universityName,majorName,year);
    }

    @Override
    public List<GraduateInfo> findBySalaryLevel(Integer salaryLevel) {
        return graduateDao.findBySalaryLevel(salaryLevel);
    }

    @Override
    public void update(Graduate graduate) {
        graduateDao.update(graduate);
    }
}
