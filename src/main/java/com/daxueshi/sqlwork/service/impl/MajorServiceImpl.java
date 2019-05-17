package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.MajorDao;
import com.daxueshi.sqlwork.domain.JobInfo;
import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Major> findByUniversityId(Integer universityId) {
        return majorDao.findByUniversityId(universityId);
    }

    @Override
    public String findNameByMajorId(Integer majorId) {
        return majorDao.findNameByMajorId(majorId);
    }

    @Override
    public List<JobInfo> findJobInfo(Integer majorId) {
        return majorDao.findJobInfo(majorId);
    }
}
