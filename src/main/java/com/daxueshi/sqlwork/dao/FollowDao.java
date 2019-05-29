package com.daxueshi.sqlwork.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-29 -10:22
 */
@Mapper
@Repository
public interface FollowDao {
    @Select("select followed_email from follows where following_email = #{email}")
    List<String> findFollowedEmail(String email);
}
