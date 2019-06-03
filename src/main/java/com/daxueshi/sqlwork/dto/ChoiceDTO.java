package com.daxueshi.sqlwork.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ChoiceDTO implements Serializable {

    public Integer year;

    public String college;

    public String major;

    //读研
    public Integer yan;

    //工作
    public Integer work;

    public Integer aboard;

    public Integer unemployment;

    public ChoiceDTO(Integer year, String college, String major, Integer yan, Integer work, Integer aboard, Integer unemployment) {
        this.year = year;
        this.college = college;
        this.major = major;
        this.yan = yan;
        this.work = work;
        this.aboard = aboard;
        this.unemployment = unemployment;
    }

    public ChoiceDTO(){}
}
