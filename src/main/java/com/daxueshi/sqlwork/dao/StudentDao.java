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


    @Insert("insert into students(email,university_name,major_name,grade,scores) values(#{email},#{universityName},#{majorName},#{grade},0)")
    void save(Student student);

    @Select("select * from students where email = #{email}")
    Student findOne(String email);

    @Delete("delete from students where email = #{email}")
    void delete(String email);

    @UpdateProvider(type = StudentProvider.class, method = "updateStudent")
    void update(Student student);

    @Select("select * from students where major_name = #{majorName} order by university_name desc ")
    List<Student> findByMajorName(String majorName);

    @Select("select * from students where major_name = #{majorName} and university_name = #{universityName}")
    List<Student> findByUniversityAndMajor(String universityName, String majorName);

    @Select("select * from students where major_name = #{majorName} and email <> #{email} order by university_name desc ")
    List<Student> findByMajorNameExceptMe(String majorName, String email);

    @Select("select * from students where major_name = #{majorName} and university_name = #{universityName} and email <> #{email}")
    List<Student> findByUniversityAndMajorExceptMe(String universityName, String majorName,String email);
}
