package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.domain.Graduate;
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
    public List<Graduate> findByUniversityId(Integer universityId) {
        return graduateDao.findByUniversityId(universityId);
    }

    @Override
    public List<Graduate> findByCompanyId(Integer companyId) {
        return graduateDao.findByCompanyId(companyId);
    }

    @Override
    public List<Graduate> findByMajorId(Integer majorId) {
        return graduateDao.findByMajorId(majorId);
    }

    @Override
    public List<Graduate> findBySalary(Double salaryMin, Double salaryMax) {
        return graduateDao.findBySalary(salaryMin,salaryMax);
    }

    @Override
    public void save(Graduate graduate) {
        //graduate.setUserId(userId);
        graduateDao.save(graduate);
    }

    @Override
    public void delete(String userId) {
        graduateDao.delete(userId);
    }

    @Override
    public void update(String userId, Graduate graduate) {
        graduate.setUserId(userId);
        graduateDao.update(graduate);
    }


}
