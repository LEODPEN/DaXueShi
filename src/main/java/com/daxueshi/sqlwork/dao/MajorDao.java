package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.JobInfo;
import com.daxueshi.sqlwork.domain.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
    @Select("select * from majors")
    @Results(id="majorMap",value={
            @Result(id=true,column = "major_id",property = "majorId"),
            @Result(column = "major_name",property = "majorName")
    })
    List <Major> findAll();

    @Select({"select * from majors where major_id in ",
            "(select major_id from uni_major where university_id=#{universityId})"})
    List <Major> findByUniversityId(Integer universityId);

    @Select("select major_name from majors where major_id = #{majorId}")
    String findNameByMajorId(Integer majorId);

    @Select("select major_name,company,city,salary,position " +
            "from majors natural join graduates natural join companies " +
            "where major_id = #{majorId}")
    List<JobInfo> findJobInfo(Integer majorId);
}
