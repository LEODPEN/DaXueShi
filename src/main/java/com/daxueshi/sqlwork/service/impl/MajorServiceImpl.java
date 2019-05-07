package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.MajorDao;
import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorDao majorDao;

    @Override
    public List<Major> findAll() {
        return majorDao.findAll();
    }

    @Cacheable(value = "majors",key = "#universityId")
    @Override
    public List<Major> findByUniversityId(Integer universityId ) {
        return majorDao.findMajorsById(universityId);
    }

}
