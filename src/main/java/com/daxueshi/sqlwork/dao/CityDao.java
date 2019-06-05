package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-05 -23:35
 */
@Mapper
@Repository
public interface CityDao {

    @Select("select * from city where province_id = #{provinceId}")
    List<City> findByProvinceId(Integer provinceId);
}
