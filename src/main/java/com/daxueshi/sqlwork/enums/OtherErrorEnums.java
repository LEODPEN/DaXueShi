package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum  OtherErrorEnums implements ResultEnums  {

    STATUS_ERROR(15,"状态错误"),
    TOKEN_ERROR(-2,"token无效或已失效"),
    NO_RIGHT(-3,"用户未认证或无权限"),
    ALREADY_FOLLOWED(-4,"已经关注该用户"),
    NO_SUNC_RECORD(-5,"未关注该用户"),
    ;

    Integer code;
    String msg;


    OtherErrorEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
