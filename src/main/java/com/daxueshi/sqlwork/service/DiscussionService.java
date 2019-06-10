package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Comment;
import com.daxueshi.sqlwork.domain.Discussion;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author onion
 * @date 2019-05-11 -10:15
 */
public interface DiscussionService {
    Page findAll(Pageable pageable, String majorName);

    Page<Discussion> findByFollow(String email, Pageable pageable);

    Page<Discussion> findByEmail(String email, Pageable pageable);

    Discussion findById(String id);

    void update(Discussion discussion);

    void deleteById(String id);

    void makeComment(Comment comment);

    PageInfo findComments(String id,Integer page, Integer size);

    void deleteComment(String discussionId, String commentId);

    void deleteCommentByCommentId(String commentId);

    void updateComment(Comment comment);

    void save(Discussion discussion, String token);

    Page<Discussion> findByKeyword(Pageable pageable, String majorName, String keyword);
}
