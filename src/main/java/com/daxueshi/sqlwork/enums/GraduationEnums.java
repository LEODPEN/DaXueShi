package com.daxueshi.sqlwork.enums;

import lombok.Getter;

/**
 * @author onion
 * @date 2019-05-26 -12:01
 */
@Getter
public enum GraduationEnums implements ResultEnums{
    WORK(1,"工作"),
    STUDY(2,"读研"),
    ABOARD(3,"出国"),
    UNEMPLOYED(4,"未就业"),
    ;
    private Integer code;
    private String msg;

    GraduationEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
