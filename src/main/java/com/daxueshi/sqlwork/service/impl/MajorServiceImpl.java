package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.MajorDao;
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
    public List<Major> findByUniversityName(String universityName) {
        return majorDao.findByUniversityName(universityName);
    }

    @Override
    public Major findMajorInfo(String majorName) {
        return majorDao.findMajorInfo(majorName);
    }
}
