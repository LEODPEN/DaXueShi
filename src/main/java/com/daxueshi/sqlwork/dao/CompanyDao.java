package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-12 -16:26
 */
@Mapper
@Repository
public interface CompanyDao {
    @Select("select * from companies")
    List<Company> findAllCompanies();

    @Select("select * from companies where company_name = #{companyName}")
    List<Company> findByName(String companyName);

    @Select("select * from companies where address = #{city}")
    List<Company> findByCity(String city);

}
