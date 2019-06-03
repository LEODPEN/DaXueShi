package com.daxueshi.sqlwork.provider;

import com.daxueshi.sqlwork.domain.Graduate;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author onion
 * @date 2019-05-17 -09:41
 */
public class GraduateProvider  {
    public String updateGraduate(final Graduate graduate){
        return new SQL(){{
            UPDATE("graduates");
            if(graduate.getUniversityName() != null){
                SET("university_name = #{universityName}");
            }
            if(graduate.getMajorName() != null){
                SET("major_name = #{majorName}");
            }
            if(graduate.getCompanyName() != null){
                SET("company_name = #{companyName}");
            }
            if(graduate.getSalary() != null){
                SET("salary = #{salary}");
            }
            if(graduate.getPosition() != null){
                SET("position = #{position}");
            }
            if(graduate.getGraduateYear() != null){
                SET("graduate_year = #{graduateYear}");
            }
            if(graduate.getState() != null){
                SET("state = #{state}");
            }
            WHERE("email = #{email}");
        }}.toString();
    }
}
