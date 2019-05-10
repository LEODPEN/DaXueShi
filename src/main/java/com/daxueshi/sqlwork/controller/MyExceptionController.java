package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author onion
 * @date 2019-05-06 -16:14
 */
@ControllerAdvice
public class MyExceptionController {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(-1,null,e.getMessage());
    }

}
