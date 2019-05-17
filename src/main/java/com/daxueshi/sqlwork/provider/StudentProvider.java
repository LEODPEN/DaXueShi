package com.daxueshi.sqlwork.provider;

import com.daxueshi.sqlwork.domain.Student;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author onion
 * @date 2019-05-17 -09:15
 */
public class StudentProvider {
    public String updateStudent(final Student student){
        return new SQL(){{
            UPDATE("students");
            if(student.getUniversityId() != null){
                SET("university_id = #{universityId}");
            }
            if(student.getMajorId() != null){
                SET("major_id = #{majorId}");
            }
            if(student.getScore() != null){
                SET("scores = #{scores}");
            }
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
