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
    String TABLE_NAME = "users";
    String INSERT_FIELD = "user_id,password,nickname,email,register_time";
    String INSERT_VALUES = "#{userId},#{password},#{nickname},#{email},#{registerTime}";
    /*@Update({"update ",TABLE_NAME," set nickname=#{nickname},phone_number=#{phoneNumber}," +
            " email=#{email},password=#{password},portrait_url=#{portraitUrl}," +
            " status=#{status},register_time=#{registerTime},last_edit_time=#{lastEditTime}" +
            " where user_id=#{userId}"})*/
    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int updateUser(User user);

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELD,") values (",INSERT_VALUES,")"})
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    int saveUser(User user);

    @Select({"select * from ",TABLE_NAME," where user_id=#{userId}"})
    User findById(String userId);

    @Select({"select * from ",TABLE_NAME," where nickname like '%${value}%' "})
    List<User> findByNickName(String nickname);

    @Select({"select count(*) from ",TABLE_NAME})
    int findTotalUser();

    @Select({"select * from ",TABLE_NAME})
    List<User> findAll();

    @Delete({"delete from ",TABLE_NAME," where user_id=#{userId}"})
    int deleteUser(String userId);

    @Select({"select * from ",TABLE_NAME," where email=#{email} and password=#{password}"})
    User findByMailAndPassword(String email,String password);

    @Select({"select * from ",TABLE_NAME," where email=#{email}"})
    User findByMail(String email);

    @Delete({"delete from ",TABLE_NAME," where email=#{email}"})
    int deleteByMail(String email);
}