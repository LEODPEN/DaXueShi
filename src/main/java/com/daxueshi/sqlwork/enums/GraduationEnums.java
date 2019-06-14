package com.daxueshi.sqlwork.enums;

import lombok.Getter;

/**
 * @author onion
 * @date 2019-05-26 -12:01
 */
@Getter
public enum GraduationEnums implements ResultEnums{
    WORK(1,"工作中"),
    STUDY(2,"读研中"),
    ABOARD(3,"出国中"),
    UNEMPLOYED(4,"暂咸鱼"),//未就业
    ;
    private Integer code;
    private String msg;

    GraduationEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
