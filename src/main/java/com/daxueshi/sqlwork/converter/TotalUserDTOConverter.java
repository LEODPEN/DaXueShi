package com.daxueshi.sqlwork.converter;


import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.dto.TotalUserDTO;
import com.daxueshi.sqlwork.enums.GraduationEnums;

public class TotalUserDTOConverter {

    public static TotalUserDTO convert(Object o, String nickname){
        TotalUserDTO totalUserDTO = new TotalUserDTO();
        totalUserDTO.setNickname(nickname);
        if (o instanceof User){
            totalUserDTO.setEmail(((User) o).getEmail());
            totalUserDTO.setRole("未认证");
            totalUserDTO.setMajorName("未知");
            totalUserDTO.setUniversityName("未知");
        }
        else if (o instanceof Student){
            totalUserDTO.setEmail(((Student) o).getEmail());
            totalUserDTO.setRole("在校生");
            totalUserDTO.setMajorName(((Student) o).getMajorName());
            totalUserDTO.setUniversityName(((Student) o).getUniversityName());
            totalUserDTO.setGradeOrYear(((Student) o).getGrade());
        }
        else {
            totalUserDTO.setEmail(((Graduate)o).getEmail());
            totalUserDTO.setRole("毕业老狗");
            totalUserDTO.setMajorName(((Graduate)o).getMajorName());
            totalUserDTO.setUniversityName(((Graduate)o).getUniversityName());
            //设置毕业年份
            totalUserDTO.setGradeOrYear(((Graduate)o).getGraduateYear());

            if (((Graduate)o).getState().equals(GraduationEnums.WORK.getCode())){
                totalUserDTO.setCompany(((Graduate)o).getCompanyName());
                totalUserDTO.setPosition(((Graduate)o).getPosition());
            }
            switch (((Graduate)o).getState()){
                case 1:
                    totalUserDTO.setState(GraduationEnums.WORK.getMsg());
                    break;
                case 2:
                    totalUserDTO.setState(GraduationEnums.STUDY.getMsg());
                    break;
                case 3:
                    totalUserDTO.setState(GraduationEnums.ABOARD.getMsg());
                    break;
                default:
                    totalUserDTO.setState(GraduationEnums.UNEMPLOYED.getMsg());
            }
        }
        return totalUserDTO;
    }

}
