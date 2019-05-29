package com.daxueshi.sqlwork.enums;

/**
 * @author onion
 * @date 2019-05-27 -07:54
 */
public enum UserEnums implements ResultEnums{
    EMAIL_REGISTERED(0,"邮箱已经被注册"),
    LOGIN_FAIL(1,"账号或密码错误"),
    INVALID_CODE(2,"验证码失效"),
    WRONG_CODE(3,"验证码错误"),
    SYNCHRONIZE_EMAIL(4,"邮箱同时注册"),
    ;
    private Integer code;
    private String msg;

    UserEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
