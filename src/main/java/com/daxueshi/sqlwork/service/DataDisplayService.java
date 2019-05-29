package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.dto.ContiOrWorkDTO;
import com.daxueshi.sqlwork.dto.DesCityDTO;
import com.daxueshi.sqlwork.dto.DesInstitutionDTO;

public interface DataDisplayService {


    DesCityDTO getDesCity(Integer year,String college, String major );

    ContiOrWorkDTO getChoice(Integer year, String college, String major);


    //which为0则返回公司，1则返回学校
    DesInstitutionDTO getInstitution(Integer year, String college, String major, Integer which);
}
