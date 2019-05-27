package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.JobInfo;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:39
 */

public interface GraduateService {

    void save(Graduate graduate);

    void delete(String email);

    List<JobInfo> findByMajorName(String majorName);

    List<JobInfo> findByUniversityNameAndMajor(String universityName, String majorName);

    List<JobInfo> findBySalaryLevel(Integer salaryLevel);

    void update(Graduate graduate);
}
