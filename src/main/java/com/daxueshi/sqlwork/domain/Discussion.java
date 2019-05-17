package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author onion
 * @date 2019-05-10 -09:27
 */
@ApiModel("论坛区实体")
@Getter
@Setter
public class Discussion implements Serializable {


    private static final long serialVersionUID = -7528116001412346790L;
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

    @Override
    public String toString() {
        return "Discussion{" +
                "_id='" + _id + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", visits=" + visits +
                ", comment=" + comment +
                ", state='" + state + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
