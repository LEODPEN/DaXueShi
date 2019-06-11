package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-06-11 -10:41
 */
@Mapper
@Repository
public interface MessageDao {

    @Select("select * from messages where email = #{email} and handle = false")
    List<Message> findMessages(String email, Integer page, Integer size);

    @Select("select * from messages where email = #{email}")
    List<Message> findAllMessages(String email);

    @Update("update messages set handle = true where email = #{email}")
    void handleAllMessages(String email);

    @Update("<script>" +
            "update messages set handle = true where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'> #{id}</foreach>" +
            "</script>")
    void handleSelectMessages(List<Integer> idList);

    @Insert("insert into messages(email,message,publish,handle) values(#{email},#{message},#{publish},false)")
    void saveMessage(String email, String message, Date publish);

    @Delete("delete from messages where email = #{email}")
    void deleteAllMessage(String email);

    @Delete("<script> " +
            "delete from messages where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'> #{id}</foreach>" +
            "</script>")
    void deleteSelectMessages(List<Integer> idList);
}
