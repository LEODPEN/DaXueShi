package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.University;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-08 -20:22
 */
@Repository
@Mapper
public interface UniversityDao {
    @Select("select * from universities")
    @Results(id="universityMap",value={
            @Result(id=true,column = "university_id",property = "universityId"),
            @Result(column = "university_name",property = "universityName"),
            @Result(column = "city",property = "city")
    })
    List<University> findAll();

    @Select({"select * from universities where university_id in " +
            "(select university_id from uni_major where major_id = #{majorId})"})
    List<University> findByMajorId(Integer majorId);

    @Select({"select * from universities where city = #{city}"})
    List<University> findByCity(String city);

    @Select("select university_name from universities where university_id = #{universityId}")
    String findNameByUniversityId(Integer universityId);
}
