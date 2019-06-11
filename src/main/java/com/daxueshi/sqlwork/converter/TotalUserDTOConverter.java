package com.daxueshi.sqlwork.converter;


import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.dto.TotalUserDTO;

public class TotalUserDTOConverter {

    public static TotalUserDTO convert(Object o, String nickname){
        TotalUserDTO totalUserDTO = new TotalUserDTO();
        totalUserDTO.setNickname(nickname);
        if (o instanceof User){
            totalUserDTO.setEmail(((User) o).getEmail());
            totalUserDTO.setMajorName("未知");
            totalUserDTO.setUniversityName("未知");
        }
        else if (o instanceof Student){
            totalUserDTO.setEmail(((Student) o).getEmail());
            totalUserDTO.setMajorName(((Student) o).getMajorName());
            totalUserDTO.setUniversityName(((Student) o).getUniversityName());
        }
        else {
            totalUserDTO.setEmail(((Graduate)o).getEmail());
            totalUserDTO.setMajorName(((Graduate)o).getMajorName());
            totalUserDTO.setUniversityName(((Graduate)o).getUniversityName());
        }
        return totalUserDTO;
    }

}
