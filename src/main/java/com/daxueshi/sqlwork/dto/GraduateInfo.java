package com.daxueshi.sqlwork.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-05-26 -11:50
 */
@Data
public class GraduateInfo implements Serializable {

    private String email;

//    private String majorName;

    private String companyName;
//    private Integer scores;
    //对应address？
    private String address;

    private Integer salary;

    private String position;

    private Integer graduateYear;

    //铁定为work，那么可以考虑去掉
    private Integer status;
}
