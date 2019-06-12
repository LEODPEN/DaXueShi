package com.daxueshi.sqlwork.handler;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author onion
 * @date 2019-05-06 -16:14
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        if (e instanceof MyException){
            return new Result(((MyException) e).getCode(),null,e.getMessage());
        }
        return new Result(-1,null,e.getMessage());
    }

}
