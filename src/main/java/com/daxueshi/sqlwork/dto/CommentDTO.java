package com.daxueshi.sqlwork.dto;

import com.daxueshi.sqlwork.domain.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-06-08 -12:05
 */
@Data
public class CommentDTO {
    private List<Comment> children;
    private String commentId;
    private String content;
    private String email;
    private Date lastEditTime;
    private String nickname;

    public CommentDTO(List<Comment> children, String commentId, String content, String email, Date lastEditTime,String nickname) {
        this.children = children;
        this.commentId = commentId;
        this.content = content;
        this.email = email;
        this.lastEditTime = lastEditTime;
        this.nickname = nickname;
    }
}
