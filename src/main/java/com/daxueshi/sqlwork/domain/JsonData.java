package com.daxueshi.sqlwork.domain;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-15 -14:42
 */
public class JsonData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code; // 0:成功 1：处理中 -1：失败
    private Object data;
    private String msg;


    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }
    public static JsonData buildSuccess(Object data){
        return new JsonData(0,data,null);
    }
    public static JsonData buildSuccess(Object data,String msg){
        return new JsonData(0,data,msg);
    }
    public static JsonData buildError(String msg){
        return new JsonData(-1,null,msg);
    }
    public static JsonData buildError(String msg,Object data){
        return new JsonData(-1,data,msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
