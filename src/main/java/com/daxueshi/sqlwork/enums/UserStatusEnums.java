package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnums {

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
