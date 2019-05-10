package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Graduate;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:39
 */

public interface GraduateService {
    List<Graduate> findByUniversityId(Integer universityId);

    List<Graduate> findByCompanyId(Integer companyId);

    List<Graduate> findByMajorId(Integer majorId);

    List<Graduate> findBySalary(Double salaryMin, Double salaryMax);

    void save(Graduate graduate);

    void delete(String userId);

    void update(String userId, Graduate graduate);
}
