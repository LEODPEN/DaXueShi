package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum  ResultEnums {

    LOGIN_SUCCESS(1,"登陆成功"),
    LOGIN_ERROR(-1,"登陆失败"),
    INFO_NOT_EXIST(-2,"查无相关信息");

    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
