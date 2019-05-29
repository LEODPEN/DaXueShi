package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author onion
 * @date 2019-05-28 -23:10
 */
@Repository
@Mapper
public interface CommentDao {
    @Insert("insert into comments(discussion_id,comment_id,parent_id,comment,email,last_edit_time)" +
            "values(#{discussionId},#{commentId},#{parentId},#{comment},#{email},#{lastEditTime})")
    void save(Comment comment);
}
