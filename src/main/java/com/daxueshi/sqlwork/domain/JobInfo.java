package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-05-17 -12:21
 */
@ApiModel("就业信息实体")
@Setter
@Getter
public class JobInfo implements Serializable {
    private String companyName;
    private String city;
    private String majorName;
    private String universityName;
    private Double salary;
    private String position;

    @Override
    public String toString() {
        return "JobInfo{" +
                "companyName='" + companyName + '\'' +
                ", city='" + city + '\'' +
                ", majorName='" + majorName + '\'' +
                ", universityName='" + universityName + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }
}
