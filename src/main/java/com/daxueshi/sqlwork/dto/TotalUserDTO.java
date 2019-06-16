package com.daxueshi.sqlwork.dto;

import lombok.Data;

@Data
public class TotalUserDTO {


    private String role;

    private String nickname;

    private String email;

    private String majorName;

    private String universityName;

    private String state;

    //在校生就是年级，毕业生就是毕业年份
    private Integer gradeOrYear;

    private String company;

    private String position;



}
