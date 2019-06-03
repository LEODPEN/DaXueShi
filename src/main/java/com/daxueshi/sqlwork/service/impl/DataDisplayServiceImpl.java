package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.ChoiceDTO;
import com.daxueshi.sqlwork.dto.DesCityDTO;
import com.daxueshi.sqlwork.dto.DesInstitutionDTO;
import com.daxueshi.sqlwork.dto.GraduateInfo;
import com.daxueshi.sqlwork.enums.GraduationEnums;
import com.daxueshi.sqlwork.enums.OtherErrorEnums;
import com.daxueshi.sqlwork.exception.MyException;
import com.daxueshi.sqlwork.service.DataDisplayService;
import com.daxueshi.sqlwork.service.GraduateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class DataDisplayServiceImpl implements DataDisplayService {

    @Autowired
    private GraduateService graduateService;

    @Override
    //只找有工作的
    public DesCityDTO getDesCity(Integer year, String college, String major) {
        List<GraduateInfo> infoList = graduateService.findGraduateJobInfoByUnameANdMnameAndYear(college,major,year);

//        List<GraduateInfo> needList = new ArrayList<>();

//        for (GraduateInfo info : infoList ){
//            if  (info.getGraduateYear().equals(year)){
//                needList.add(info);
//            }
//        }
        DesCityDTO desCityDTO = new DesCityDTO(year,college,major);
        desCityDTO.setTotal(infoList.size());
        Map<String,Integer> map = new ConcurrentHashMap<>();
        Integer count = 0;
        for (GraduateInfo info:infoList){
            String city = info.getAddress();
//            map.putIfAbsent(city,1);
            map.merge(city,1, (oldValue,newValue)-> oldValue+newValue);
//            Integer v =map.get(city);
//            //map.merge()
//            if ( v==null){
//                map.put(city,1);
//            }
//            else {
//                map.put(city,v+1);
//            }
        }
        desCityDTO.setCityWithCount(map);

        return desCityDTO;

    }

    @Override
    public ChoiceDTO getChoice(Integer year, String college, String major) {
        List<Graduate> graduateList = graduateService.findByUniversityNameAndMajorAndYear(college,major,year);

        int study;
        int work;
        int aboard;
        int ue;
        study = work = aboard = ue =0;
        //  用不了switch
        for (Graduate graduate : graduateList){
            Integer status = graduate.getState();
            if (status.equals(GraduationEnums.STUDY.getCode())){
                study++;
            }
            else if (status.equals(GraduationEnums.WORK.getCode())){
                work++;
            }
            else if (status.equals(GraduationEnums.ABOARD.getCode())){
                aboard++;
            }else if (status.equals(GraduationEnums.UNEMPLOYED.getCode())){
                ue++;
            }else{
                log.error(OtherErrorEnums.STATUS_ERROR.getMsg());
                throw new MyException(OtherErrorEnums.STATUS_ERROR);
            }
        }
        ChoiceDTO choiceDTO = new ChoiceDTO(year,
                college,
                major,
                study,
                work,
                aboard,
                ue);
        return choiceDTO;

    }

    @Override
    //目前只展示公司的去向
    public DesInstitutionDTO getInstitution(Integer year, String college, String major, Integer which) {

        List<GraduateInfo> infoList = graduateService.findGraduateJobInfoByUnameANdMnameAndYear(college,major,year);

        Map<String,Integer> desMap = new ConcurrentHashMap<>();

        if (which.equals(GraduationEnums.STUDY.getCode())){
//            for (GraduateInfo info : infoList){
//                if (info.getStatus().equals(GraduationEnums.STUDY.getCode())){
//                    //todo 读研院校并未体现？
//                    //先放个假数据
//                    desMap.merge("ECNU",1,(oldValue,newValue)->oldValue+newValue);
//                }
//            }
            return null;
        }else{
            for (GraduateInfo info : infoList){
                if (info.getStatus().equals(GraduationEnums.WORK.getCode())){
                    desMap.merge(info.getCompanyName(),1,(oldValue,newValue)->oldValue+newValue);
                }
            }
        }
        DesInstitutionDTO desInstitutionDTO = new DesInstitutionDTO(year,college,major,infoList.size(),desMap);

        return desInstitutionDTO;
    }
}
