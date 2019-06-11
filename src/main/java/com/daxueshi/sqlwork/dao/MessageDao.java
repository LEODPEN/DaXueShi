package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-11 -10:41
 */
@Mapper
@Repository
public interface MessageDao {
    @Select("select * from messages where email = #{email} and handle = false")
    List<Message> messages(String email);

    @Update("update messages set handle = true where id = #{id}")
    void handleMessage(Integer id);

    @Update("update messages set handle = true where id in #{idList}")
    void handleAllMessages(List<Integer> idList);

    @Insert("insert into messages(email,message,publish,handle) values(#{email},#{message},#{publish},false)")
    void hanle();

}
