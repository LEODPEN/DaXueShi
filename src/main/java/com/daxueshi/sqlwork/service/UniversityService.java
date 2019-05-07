package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.University;

import java.util.List;

public interface UniversityService {

    List<University> findAll();

    List<University> findByMajorId(Integer majorId);
}
