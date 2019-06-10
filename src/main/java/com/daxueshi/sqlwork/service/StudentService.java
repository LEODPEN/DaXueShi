package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:10
 */
public interface StudentService {

    List<Student> findByMajorName(String majorName);

    PageInfo findByMajorName(String email,Integer page, Integer size);

    void save(Student student);

    void delete(String email);

    void update(Student student);

    List<Student> findByUniversityAndMajor(String universityName, String majorName);

    PageInfo findByUniversityAndMajor(String email, Integer page, Integer size);
}
