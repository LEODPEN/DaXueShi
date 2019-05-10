package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.CompanyDao;
import com.daxueshi.sqlwork.domain.Company;
import com.daxueshi.sqlwork.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public List<Company> findByCity(String city) {
        return companyDao.findByCity(city);
    }

    @Override
    public List<Company> findByType(String type) {
        return companyDao.findByType(type);
    }
}
