package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.GraduateInfo;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:39
 */

public interface GraduateService {

    void save(Graduate graduate);

    void delete(String email);

    List<GraduateInfo> findByMajorName(String majorName);

    List<Graduate> findByUniversityNameAndMajor(String universityName, String majorName);

    List<GraduateInfo> findGraduateJobInfoByUnameANdMname(String universityName, String majorName);

    List<Graduate> findByUniversityNameAndMajorAndYear(String universityName, String majorName, Integer year);

    List<GraduateInfo> findGraduateJobInfoByUnameANdMnameAndYear(String universityName, String majorName, Integer year);


    List<GraduateInfo> findBySalaryLevel(Integer salaryLevel);

    void update(Graduate graduate);
}
