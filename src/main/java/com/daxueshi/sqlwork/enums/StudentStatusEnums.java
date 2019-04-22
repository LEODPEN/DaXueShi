package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum  StudentStatusEnums {

    study(1,"在校"),
    work(2,"工作"),
    ;


    private Integer code;

    private String msg;

    StudentStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
