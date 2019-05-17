package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.provider.GraduateProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -16:39
 */
@Repository
@Mapper
public interface GraduateDao {
    @Select("select * from graduates where universityId=#{universityId}")
    List<Graduate> findByUniversityId(Integer universityId);
    @Select("select * from graduates where companyId=#{companyId}")
    List<Graduate> findByCompanyId(Integer companyId);
    @Select("select * from graduates where majorId=#{majorId}")
    List<Graduate> findByMajorId(Integer majorId);
    @Select("select * from graduates where salary between #{salaryMin} and #{salaryMax}")
    List<Graduate> findBySalary(Double salaryMin, Double salaryMax);
    @InsertProvider(type = GraduateProvider.class, method = "insertGraduate")
    void save(Graduate graduate);
    @Delete("delete from graduates where useId=#{userId}")
    void delete(String userId);
    @UpdateProvider(type = GraduateProvider.class, method = "updateGraduate")
    void update(Graduate graduate);
}
