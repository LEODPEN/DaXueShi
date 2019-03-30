package com.daxueshi.sqlwork.VO;

import lombok.Data;

/*http返回的对象或者是最外层对象-前后交互*/
@Data
public class ResultVO<T> {
    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*具体内容*/
    private T data;
}
