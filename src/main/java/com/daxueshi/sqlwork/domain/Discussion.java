package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author onion
 * @date 2019-05-10 -09:27
 */
@ApiModel("论坛区实体")
public class Discussion implements Serializable {
    @ApiModelProperty("论坛Id")
    private String _id;
    private String content;
    private Date publishTime;
    @ApiModelProperty("用户Id")
    private String userId;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("访问量")
    private Integer visits;
    @ApiModelProperty("回复量")
    private Integer comment;
    private String state;
    @ApiModelProperty("上级用户Id")
    private String parentId;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
