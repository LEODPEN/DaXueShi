package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.dto.ChoiceDTO;
import com.daxueshi.sqlwork.dto.DesCityDTO;
import com.daxueshi.sqlwork.dto.DesInstitutionDTO;

import java.util.Map;

public interface DataDisplayService {


    DesCityDTO getDesCity(Integer year,String college, String major );

    ChoiceDTO getChoice(Integer year, String college, String major);


    //which为0则返回公司，1则返回学校
    DesInstitutionDTO getInstitution(Integer year, String college, String major, Integer which);

    Map<String,Object> getSalaryTrend(Integer year, String college, String major);
}
