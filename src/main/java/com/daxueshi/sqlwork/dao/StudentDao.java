package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.provider.StudentProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:10
 */
@Mapper
@Repository
public interface StudentDao {

    @Select("select * from students where university_id = #{universityId}")
    List<Student> findByUniversityId(Integer universityId);

    @Select("select * from students where major_id = #{majorId}")
    List<Student> findByMajorid(Integer majorId);

    @Insert("insert into students(user_id,university_id,major_id,scores) values(#{userId},#{universityId},#{majorId},0)")
    void save(Student student);

    @Delete("delete from students where user_id = #{userId}")
    void delete(String userId);

    @UpdateProvider(type = StudentProvider.class, method = "updateStudent")
    void update(Student student);
}
