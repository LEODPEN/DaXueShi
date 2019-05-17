package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum  ResultEnums {

    LOGIN_SUCCESS(1,"登陆成功"),
    LOGIN_ERROR(2,"账号或密码错误"),
    INFO_NOT_EXIST(3,"查无相关信息"),
    NO_SUCH_USER(4,"无此用户"),
    USER_EXIST(5,"邮箱已被注册"),
    WRONG_CODE(6,"验证码错误"),
    INVALID_CODE(7,"验证码失效");


    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
