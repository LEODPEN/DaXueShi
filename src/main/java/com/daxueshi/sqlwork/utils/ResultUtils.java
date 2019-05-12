package com.daxueshi.sqlwork.utils;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.enums.ResultEnums;

public class ResultUtils {
    public static Result success(Object object){
        Result result = new Result();

        if (object instanceof ResultEnums){
            result.setCode(((ResultEnums) object).getCode());
            result.setMsg(((ResultEnums) object).getMsg());
            return result;
        }

        result.setData(object);
        result.setCode(0);
        result.setMsg("success");
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(ResultEnums resultEnums) {
        Result result = new Result();
        result.setMsg(resultEnums.getMsg());
        result.setCode(resultEnums.getCode());
        return result;
    }

    public static Result withMessage(String message){
        Result result = new Result();
        result.setMsg(message);
        result.setCode(0);
        return result;
    }
}
