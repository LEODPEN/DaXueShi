package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.JobInfo;
import com.daxueshi.sqlwork.provider.GraduateProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:39
 */
@Repository
@Mapper
public interface GraduateDao {
    @Insert("insert into graduates(email,university_name,major_name,company_name,score,salary,position,graduate_year,status)" +
            "values(#{email},#{universityName},#{majorName},#{companyName},#{score},#{salary},#{position},#{graduateYear},#{status})")
    void save(Graduate graduate);

    @Delete("delete from graduates where email=#{email}")
    void delete(String email);

    @UpdateProvider(type = GraduateProvider.class, method = "updateGraduate")
    void update(Graduate graduate);

    @Select("select * from graduates " +
            "where major_name = #{majorName} " +
            "left join companies" +
            "on graduates.company_name = companies.company_name")
    List<JobInfo> findByMajorName(String majorName);

    @Select("select * from graduates " +
            "where university_name = #{universityName} and major_name = #{majorName} " +
            "left join companies" +
            "on graduates.company_name = companies.company_name")
    List<JobInfo> findByUniversityAndMajor(String universityName, String majorName);

    @Select("select * from graduates " +
            "where salary = #{salaryLevel} " +
            "left join companies" +
            "on graduates.company_name = companies.company_name")
    List<JobInfo> findBySalaryLevel(Integer salaryLevel);
}
