package com.daxueshi.sqlwork.RequestDataForm;


import lombok.Data;

@Data
//是否加验证
public class RequestForm {

    public String college;

    public String major;

    public Integer year;

    //如果传的是请求体就传token，否则直接getMapping操作过来
    public String token;
}
