package com.daxueshi.sqlwork.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class DesInstitutionDTO {

    //学生查本专业的应届生毕业公司流向（地点流向）
    // 也可以作为毕业的读研学校流向

    //做成pie图,查看详情就给出详情的list

    //毕业年份
    Integer year;

    String college;

    String major;

    Integer total;

    Map<String,Integer> nameWithCount;

    public DesInstitutionDTO(Integer year, String college, String major) {
        this.year = year;
        this.college = college;
        this.major = major;
    }

    public DesInstitutionDTO(){}
}
