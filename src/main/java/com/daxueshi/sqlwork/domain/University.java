package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:15
 */
@ApiModel("大学实体")
@Data
public class University implements Serializable {

    private static final long serialVersionUID = 7716883622038586704L;
    private String universityName;
    private String city;
    private String image;

}