package com.daxueshi.sqlwork.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-15 -14:42
 */

/*http返回的对象或者是最外层对象-前后交互*/
    //
@Data
public class Result<T> implements Serializable {


    //使用插件生成较佳
    private static final long serialVersionUID = 2882512985838243173L;

    private Integer code; // 0:成功 1：处理中 -1：失败

    //改为范型
    private T data;

    private String msg;


    public Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result() {
    }

    //看看此处还有没有其他的用途，无则可删除
    public static Result buildSuccess(){
        return new Result(0,null,null);
    }
    public static Result buildSuccess(Object data){
        return new Result(0,data,null);
    }
    public static Result buildSuccess(Object data, String msg){
        return new Result(0,data,msg);
    }
    public static Result buildError(String msg){
        return new Result(-1,null,msg);
    }
    public static Result buildError(String msg, Object data){
        return new Result(-1,data,msg);
    }


}
