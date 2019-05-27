package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Student;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:10
 */
public interface StudentService {

    List<Student> findByMajorName(String majorName);

    void save(Student student);

    void delete(String email);

    void update(Student student);

    List<Student> findByUniversityAndMajor(String universityName, String majorName);
}
