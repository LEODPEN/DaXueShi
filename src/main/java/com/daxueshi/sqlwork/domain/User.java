package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class User implements Serializable {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
