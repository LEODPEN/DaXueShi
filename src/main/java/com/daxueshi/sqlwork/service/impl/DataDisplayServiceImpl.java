package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.dto.ChoiceDTO;
import com.daxueshi.sqlwork.dto.DesCityDTO;
import com.daxueshi.sqlwork.dto.DesInstitutionDTO;
import com.daxueshi.sqlwork.dto.GraduateInfo;
import com.daxueshi.sqlwork.enums.GraduationEnums;
import com.daxueshi.sqlwork.enums.OtherErrorEnums;
import com.daxueshi.sqlwork.enums.SalaryEnums;
import com.daxueshi.sqlwork.exception.MyException;
import com.daxueshi.sqlwork.service.DataDisplayService;
import com.daxueshi.sqlwork.service.GraduateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;

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

    @Override
    public Map<String, Object> getSalaryTrend(Integer year, String college, String major) {

        Map<String,Object> salaryMap = new ConcurrentHashMap<>();
        List<Integer> yearList = new ArrayList<>(asList(year-4,year-3,year-2,year-1,year));
        List<Integer> salaryList = new ArrayList<>();
        salaryMap.put("year",yearList);
        for (int i = 0; i<5;i++){
            List< GraduateInfo> infoList = graduateService.findGraduateJobInfoByUnameANdMnameAndYear(college,major,year-i);
            if (CollectionUtils.isEmpty(infoList)){
                salaryList.add(0);
                continue;
            }
            int average = 0;
            //是以级别来定的资薪水平---暂时定8级，一级5000
            for (GraduateInfo info : infoList){
                average+=info.getSalary();
            }
            average/=infoList.size();
            if (average<=0){
                salaryList.add(0);
            }else if (average>8){
                salaryList.add(50000);
            }else {
                for (SalaryEnums enums : SalaryEnums.values()){
                    if (enums.getLevel()==average){
                        salaryList.add(enums.getAverage());
                        break;
                    }
                }
            }

        }
        salaryMap.put("salary",salaryList);
        return salaryMap;
    }
}
