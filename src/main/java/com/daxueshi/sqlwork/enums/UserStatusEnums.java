package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnums {
    //已经激活账号但在校或工作状态保密
    SECRETBUTVERIFIED(0,"保密"),

    STUDY(1,"在校"),

    WORK(2,"工作"),

    TOBEVERIFIED(-1,"未激活邮箱"),
    ;



    private Integer code;

    private String msg;

    UserStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
