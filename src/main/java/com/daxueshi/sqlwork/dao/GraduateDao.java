package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.GraduateInfo;
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
    @Insert("insert into graduates(email,university_name,major_name,company_name,salary,position,graduate_year,state)" +
            "values(#{email},#{universityName},#{majorName},#{companyName},#{salary},#{position},#{graduateYear},#{status})")
    void save(Graduate graduate);

    @Delete("delete from graduates where email=#{email}")
    void delete(String email);

    @UpdateProvider(type = GraduateProvider.class, method = "updateGraduate")
    void update(Graduate graduate);

    @Select("select * from graduates " +
            "left join companies " +
            "on graduates.company_name = companies.company_name " +
            "where major_name = #{majorName} " )
    List<GraduateInfo> findByMajorName(String majorName);

    @Select("select * from graduates from "+
            "graduates where university_name = #{universityName} and major_name = #{majorName} ")
    List<Graduate> findAllByUniversityAndMajor(String universityName, String majorName);

    @Select("select email, company_name, address, salary, position, graduate_year, state as status "+
            "from graduates natural join companies " +
            "using company_name " +
            "where university_name = #{universityName} and major_name = #{majorName} ")
    List<GraduateInfo> findAllGraduateInfoByUniversityAndMajor(String universityName, String majorName);

    @Select("select * from graduates from "+
            "graduates where university_name = #{universityName} and major_name = #{majorName} and graduate_year = #{year}")
    List<Graduate> findByUniversityAndMajorAndYear(String universityName, String majorName, Integer year);

    @Select("select email, company_name, address, salary, position, graduate_year, state as status "+
            "from graduates natural join companies " +
            "using company_name " +
            "where university_name = #{universityName} and major_name = #{majorName} ")
    List<GraduateInfo> findGraduateInfoByUniversityAndMajorAndYear(String universityName, String majorName, Integer year);

    @Select("select * from graduates " +
            "left join companies " +
            "on graduates.company_name = companies.company_name " +
            "where salary = #{salaryLevel} ")
    List<GraduateInfo> findBySalaryLevel(Integer salaryLevel);
}
