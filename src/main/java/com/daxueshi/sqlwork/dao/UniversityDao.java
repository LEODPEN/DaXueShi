package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.University;
import org.apache.ibatis.annotations.Mapper;
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
    @Select({"select * from universities where city = #{city}"})
    List<University> findByCity(String city);
}
