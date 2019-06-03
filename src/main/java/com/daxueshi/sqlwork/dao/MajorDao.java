package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Major;
import com.daxueshi.sqlwork.dto.GraduateInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-08 -20:32
 */
@Repository
@Mapper
public interface MajorDao {


    @Select({"select major_name from majors where major_name in ",
            "(select major_name from offers where university_name=#{universityName})"})
    List <Major> findByUniversityName(String universityName);


    @Select("select major_name,company_name,city,salary,position " +
            "from majors natural join graduates natural join companies " +
            "where major_id = #{majorId}")
    List<GraduateInfo> findJobInfoByMajor(Integer majorId);

    @Select("select * from majors where major_name = #{majorName}")
    Major findMajorInfo(String majorName);
}
