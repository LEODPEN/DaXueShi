package com.daxueshi.sqlwork.dao;

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
    String TABLE_NAME = "majors";

    @Select({"select * from ",TABLE_NAME})
    @Results(id="majorMap",value={
            @Result(id=true,column = "major_id",property = "majorId"),
            @Result(column = "major_name",property = "majorName")
            /*@Result(column = "major_id",property = "universityList",
                    many = @Many(
                            select = "com.daxueshi.sqlwork.dao.UniversityDao.findUniversitiesById",
                            fetchType = FetchType.LAZY
                    )

            )*/

    })
    List <Major> findAll();

    @Select({"select * from ",TABLE_NAME," where major_id in ",
            "(select major_id from uni_major where university_id=#{universityId})"})
    List <Major> findMajorsById(Integer universityId);
}
