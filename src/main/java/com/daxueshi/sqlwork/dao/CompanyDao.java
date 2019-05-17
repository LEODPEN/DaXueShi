package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Company;
import com.daxueshi.sqlwork.domain.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:32
 */
@Mapper
@Repository
public interface CompanyDao {
    @Select("select * from companies")
    List<Company> findAll();
    @Select("select * from companies where type like #{type}")
    List<Company> findByType(String type);
    @Select("select * from companies where city = #{city}")
    List<Company> findByCity(String city);
    @Select("select * from companies where company_id = #{companyId}")
    Company findByCompanyId(Integer companyId);

    @Select("select company_name,city,salary,position from companies natural join graduates where companyId = #{companyId}")
    List<JobInfo> findJobInfo(Integer companyId);
}
