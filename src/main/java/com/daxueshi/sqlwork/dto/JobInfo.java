package com.daxueshi.sqlwork.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-05-26 -11:50
 */
@Data
public class JobInfo implements Serializable {
    private String email;
    private String majorName;
    private String companyName;
    private Integer scores;
    private String city;
    private Integer salary;
    private String position;
    private Integer graduateYear;
    private Integer status;
}
