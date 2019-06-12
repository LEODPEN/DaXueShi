package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Interview;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-12 -16:19
 */
@Mapper
@Repository
public interface InterviewDao {
    @Select("select * from interviews where company = #{company}")
    List<Interview> findAllInterviews(String company);

    @Delete("delete from interviews where id = #{id}")
    void deleteInterview(Integer id);

    @Insert("insert into interviews(company,rate,feeling,nickname) " +
            "values(#{company},#{rate},#{feeling},#{nickname})")
    void addInterview(Interview interview);
}
