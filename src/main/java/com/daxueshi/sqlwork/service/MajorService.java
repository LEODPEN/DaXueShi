package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.JobInfo;
import com.daxueshi.sqlwork.domain.Major;

import java.util.List;

public interface MajorService {
     List<Major> findAll();
     List<Major> findByUniversityId(Integer universityId);
     String findNameByMajorId(Integer majorId);
     List<JobInfo> findJobInfo(Integer majorId);
}
