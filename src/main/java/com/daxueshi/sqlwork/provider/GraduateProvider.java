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
            if(graduate.getUniversityId() != null){
                SET("university_id = #{universityId}");
            }
            if(graduate.getMajorId() != null){
                SET("major_id = #{majorId}");
            }
            if(graduate.getCompanyId() != null){
                SET("company_id = #{companyId}");
            }
            if(graduate.getScore() != null){
                SET("score = #{score}");
            }
            if (graduate.getSalary() != null){
                SET("salary = #{salary}");
            }
            if(graduate.getPosition() != null){
                SET("position = #{position}");
            }
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
