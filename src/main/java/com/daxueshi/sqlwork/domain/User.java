package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
@Data
public class User implements Serializable {


    private static final long serialVersionUID = -2027019289964426820L;
    private String nickname;
    @Email(message = "邮箱格式错误")
    private String email;
    @NotNull
    @Size(min = 6, max = 20, message = "密码长度为6-20位")
    private String password;
    private String profile;
    private Integer status;
    private Date registerTime;
    private Date lastLoginTime;

}
