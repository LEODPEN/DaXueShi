package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnums implements ResultEnums{
    //已经激活账号但在校或工作状态保密

    VISITOR(0,"普通用户"),

    STUDENT(1,"在校"),

    GRADUATE(2,"工作"),

    ;



    private Integer code;

    private String msg;

    UserStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
