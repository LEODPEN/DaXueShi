package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Student;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:10
 */
public interface StudentService {
    List<Student> findByUniversityId(Integer universityId);

    List<Student> findByMajorId(Integer majorId);

    void save(Student student);

    void delete(String userId);

    void update(String userId, Student student);
}
