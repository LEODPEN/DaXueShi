package com.daxueshi.sqlwork.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DesCityDTO {

    //学生查本专业的应届生毕业地点流向（包括工作和读研）

    //做成pie图

    public Integer year;

    public String college;

    public String major;

    public Integer total;

    public Map<String,Integer> cityWithCount;

    public DesCityDTO(Integer year, String college, String major) {
        this.year = year;
        this.college = college;
        this.major = major;
    }
    public DesCityDTO(){};
}
