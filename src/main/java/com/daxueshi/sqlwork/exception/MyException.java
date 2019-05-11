package com.daxueshi.sqlwork.exception;

import com.daxueshi.sqlwork.enums.ResultEnums;
import lombok.Getter;

@Getter
public class MyException extends RuntimeException{

    private Integer code;

    public MyException(ResultEnums resultEnums){
        super(resultEnums.getMsg());
        this.code=resultEnums.getCode();
    }

    public MyException(String msg, Integer code){
        super(msg);
        this.code=code;
    }
}
