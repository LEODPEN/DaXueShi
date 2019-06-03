package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum  OtherErrorEnums implements ResultEnums  {

    STATUS_ERROR(15,"状态错误"),
    TOKEN_ERROR(-2,"token无效或已失效"),
    ;

    Integer code;
    String msg;


    OtherErrorEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
