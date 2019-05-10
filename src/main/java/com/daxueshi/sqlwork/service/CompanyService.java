package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    List<Company> findByCity(String city);

    List<Company> findByType(String type);
}
