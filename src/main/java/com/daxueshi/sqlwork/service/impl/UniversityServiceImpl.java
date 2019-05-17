package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.UniversityDao;
import com.daxueshi.sqlwork.domain.University;
import com.daxueshi.sqlwork.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityDao universityDao;

    @Override
    public List<University> findAll() {
        return universityDao.findAll();
    }

    @Override
    public List<University> findByMajorId(Integer majorId) {
        return universityDao.findByMajorId(majorId);
    }

    @Override
    public List<University> findByCity(String city) {
        return universityDao.findByCity(city);
    }

    @Override
    public String findNameByUniversityId(Integer universityId) {
        return universityDao.findNameByUniversityId(universityId);
    }
}
