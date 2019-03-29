package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * @author onion
 * @create 2019-03-29-15:02
 */
@Mapper
public interface UserDao {
    @Insert({"insert into user(name,password) values(#{name},#{password}"})
    int addUser(User user);

    @Update({"update user set password = #{password} where id = {#id}"})
    void updatePassword(User user);

    @Select({"select * from user where id = #{id}"})
    User findUserById();

    @Delete({"delete from user where id = #{id}"})
    void deleteById(int id);
}
