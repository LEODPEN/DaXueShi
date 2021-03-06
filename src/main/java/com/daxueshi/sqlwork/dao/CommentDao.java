package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-28 -23:10
 */
@Repository
@Mapper
public interface CommentDao {
    @Insert("insert into comments(discussion_id,comment_id,parent_id,content,email,last_edit_time,nickname)" +
            "values(#{discussionId},#{commentId},#{parentId},#{content},#{email},#{lastEditTime},#{nickname})")
    void save(Comment comment);

    @Select("select * from comments where discussion_id = #{id}")
    List<Comment> findById(String id);

    @Select("select * from comments where parent_id = #{parentId}")
    List<Comment> findChildrenById(String parentId);

    @Delete("delete from comments where discussion_id = #{id}")
    void deleteByDiscussionId(String id);

    @Delete("delete from comments where discussion_id = #{discussionId} and comment_id = #{commentId}")
    void deleteCertainComment(String discussionId, String commentId);

    @Delete("delete from comments where comment_id = #{commentId}")
    void deleteCommentByCommentId(String commentId);

    @Update("update comments set content = #{content}, last_edit_time = #{lastEditTime} " +
            "where discussion_id = #{discussionId} and comment_id = #{commentId}")
    void update(Comment comment);
}
