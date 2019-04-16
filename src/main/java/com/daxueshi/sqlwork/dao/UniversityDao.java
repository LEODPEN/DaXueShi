package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.University;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-08 -20:22
 */
@Repository
@Mapper
public interface UniversityDao {
    String TABLE_NAME = "universities";
    @Select({"select * from ",TABLE_NAME})
    @Results(id="universityMap",value={
            @Result(id=true,column = "university_id",property = "universityId"),
            @Result(column = "university_name",property = "universityName"),
            @Result(column = "university_id",property = "majorList",
                    many = @Many(
                            select = "com.daxueshi.sqlwork.dao.MajorDao.findMajorsById",
                            fetchType = FetchType.LAZY
                    )

            )

    })
    List<University> findAll();

    @Select({"select * from ",TABLE_NAME," where university_id in " +
            "(select university_id from uni_major where major_id = #{majorId})"})
    List<University> findUniversitiesById(Integer majorId);
}
