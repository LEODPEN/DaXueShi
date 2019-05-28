package com.daxueshi.sqlwork.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ContiOrWorkDTO implements Serializable {

    public Integer year;

    public String college;

    public String major;

    //读研
    public Integer yan;

    //工作
    public Integer work;

    public ContiOrWorkDTO(Integer year, String college, String major) {
        this.year = year;
        this.college = college;
        this.major = major;
    }
    public ContiOrWorkDTO(){}
}
