package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:10
 */
@Mapper
@Repository
public interface StudentDao {
    List<Student> findByUniversityId(Integer universityId);

    List<Student> findByMajorid(Integer majorId);

    void save(Student student);

    void delete(String userId);

    void update(String userId, Student student);
}
