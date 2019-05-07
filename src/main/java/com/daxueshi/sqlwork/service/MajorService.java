package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Major;

import java.util.List;

public interface MajorService {
    public List<Major> findAll();
    public List<Major> findByUniversityId(Integer universityId);
}
