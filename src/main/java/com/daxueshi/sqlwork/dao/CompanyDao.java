package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:32
 */
@Mapper
@Repository
public interface CompanyDao {
    public List<Company> findAll();

    List<Company> findByType(String type);

    List<Company> findByCity(String city);
}
