package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author onion
 * @date 2019-05-28 -22:14
 */
@Data
@ApiModel("讨论实体")
public class Comment implements Serializable {
    private String discussionId;
    private String commentId;
    private String parentId;
    private String content;
    private String email;
    private Date lastEditTime;
}
