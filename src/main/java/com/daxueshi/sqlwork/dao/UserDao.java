package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-08 -20:56
 */
@Repository
@Mapper
public interface UserDao {

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    void updateUser(User user);

    @Insert("insert into users(email,password,nickname,profile,status,register_time,last_login_time)" +
            "values(#{email},#{password},#{nickname},#{profile},#{status},#{registerTime},#{lastLoginTime})")
    void saveUser(User user);

    @Select("select * from users where email=#{email}")
    User findByMail(String email);

    @Delete("delete from users where email=#{email}")
    void deleteByMail(String email);

    @Select("select * from users where nickname like CONCAT('%',#{nickname},'%') ")
    List<User> findByNickname(String nickname);

}
