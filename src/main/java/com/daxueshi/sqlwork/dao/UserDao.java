package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author onion
 * @date 2019-04-08 -20:56
 */
@Repository
@Mapper
public interface UserDao {

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    void updateUser(User user);

    @InsertProvider(type = UserProvider.class, method = "insertUser")
    void saveUser(User user);

    @Select("select * from users where email=#{email}")
    User findByMail(String email);

    @Delete("delete from users where email=#{email}")
    void deleteByMail(String email);

    @Insert("insert into follows(following_email,followed_email,times)" +
            "values(#{followingEmail},#{followedEmail},0)")
    void follow(String followingEmail,String followedEmail);

    @Delete("delete from follows " +
            "where following_email = #{followingEmail}" +
            "and followed_email = #{followedEmail}")
    void cancelFollow(String followingEmail,String followedEmail);

    @Update("update follows set times = times + 1" +
            "where following_email = #{followingEmail}" +
            "and followed_email = #{followedEmail}")
    void recordTimes(String followingEmail,String followedEmail);
}