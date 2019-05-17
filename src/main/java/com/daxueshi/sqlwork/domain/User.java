package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author onion
 * @date 2019-04-08 -20:36
 */
@ApiModel("用户实体")
@Setter
@Getter
public class User implements Serializable {


    private static final long serialVersionUID = -2027019289964426820L;
    private String userId;
    private String nickname;
    private String phoneNumber;
    @Email(message = "邮箱格式错误")
    private String email;
    @NotNull
    @Size(min = 6, max = 20, message = "密码长度为6-20位")
    private String password;
    @ApiModelProperty("头像路径")
    private String portraitUrl;
    @ApiModelProperty("账号状态")
    private Integer status;
    private Date registerTime;
    private Date lastEditTime;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", portraitUrl='" + portraitUrl + '\'' +
                ", status=" + status +
                ", registerTime=" + registerTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }

}
