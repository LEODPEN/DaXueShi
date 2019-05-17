package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:15
 */
@ApiModel("大学实体")
@Setter
@Getter
public class University implements Serializable {


    private static final long serialVersionUID = 7716883622038586704L;
    private Integer universityId;
    private String universityName;
    private String city;

    @Override
    public String toString() {
        return "University{" +
                "universityId=" + universityId +
                ", universityName='" + universityName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}