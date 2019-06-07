package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author onion
 * @date 2019-05-10 -09:27
 */
@ApiModel("论坛区实体")
@Data
@Document(collection = "discussions")
@DynamicUpdate
public class Discussion implements Serializable {


    private static final long serialVersionUID = -7528116001412346790L;
    @ApiModelProperty("论坛Id")
    @Id
    private String id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String majorName;
    private Date publishTime;
    private Date lastEditTime;
    private String email;
    private String nickname;
    private Integer visits;
    private Integer comments;
    private Integer thumbs;

}
