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
            if(student.getUniversityName() != null){
                SET("university_name = #{universityName}");
            }
            if(student.getMajorName() != null){
                SET("major_name = #{majorName}");
            }
            if(student.getScores() != null){
                SET("scores = #{scores}");
            }
            if(student.getGrade() != null){
                SET("grade = #{grade}");
            }
            WHERE("email = #{email}");
        }}.toString();
    }
}
