package com.daxueshi.sqlwork.provider;

import com.daxueshi.sqlwork.domain.Graduate;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author onion
 * @date 2019-05-17 -09:41
 */
public class GraduateProvider  {
    public String insertGraduate(final Graduate graduate){
        return new SQL(){{
            INSERT_INTO("graduates");
            if(graduate.getUserId() != null){
                VALUES("user_id",graduate.getUserId());
            }
            if(graduate.getUniversityId() != null){
                VALUES("university_id",graduate.getUniversityId().toString());
            }
            if(graduate.getMajorId() != null){
                VALUES("major_id",graduate.getMajorId().toString());
            }
            if(graduate.getCompanyId() != null){
                VALUES("company_id",graduate.getCompanyId().toString());
            }
            if(graduate.getScores() != null){
                VALUES("score",graduate.getScores().toString());
            }
            if (graduate.getSalary() != null){
                VALUES("salary",graduate.getSalary().toString());
            }
            if(graduate.getPosition() != null){
                VALUES("position",graduate.getPosition());
            }
        }}.toString();
    }
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
            if(graduate.getScores() != null){
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
