package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Follow;
import org.apache.ibatis.annotations.*;
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

    @Select("select * from follows where following_email = #{email} order by times desc")
    List<Follow> findIFollowWho(String email);

    @Select("select * from follows where followed_email = #{email} order by times desc")
    List<Follow> findWhoFollowMe(String email);

    @Delete("delete from follows where following_email = #{followingEmail} and followed_email = #{followedEmail}")
    void cancelFollow(String followingEmail, String followedEmail);

    @Insert("insert into follows(following_email,followed_email,times) values(#{followingEmail},#{followedEmail},0)")
    void addFollow(String followingEmail, String followedEmail);

    @Update("update follows set times = times + 1 " +
            "where following_email = #{followingEmail}" +
            "and followed_email = #{followedEmail}")
    void recordTimes(String followingEmail,String followedEmail);
}
