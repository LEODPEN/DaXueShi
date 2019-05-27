package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:49
 */
@Data
@ApiModel("学生实体")
public class Student implements Serializable {


    private static final long serialVersionUID = -7311090085844484220L;
    private String email;
    private String universityName;
    private String majorName;
    private Integer scores;
    private Integer grade;

}
