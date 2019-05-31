package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Major;

import java.util.List;

public interface MajorService {
    List<Major> findByUniversityName(String universityName);

    Major findMajorInfo(String majorName);
}
