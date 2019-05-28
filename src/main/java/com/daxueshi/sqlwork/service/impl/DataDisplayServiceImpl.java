package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dto.ContiOrWorkDTO;
import com.daxueshi.sqlwork.dto.DesCityDTO;
import com.daxueshi.sqlwork.dto.DesInstitutionDTO;
import com.daxueshi.sqlwork.dto.JobInfo;
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
    public DesCityDTO getDesCity(Integer year, String college, String major) {
        List<JobInfo> infoList = graduateService.findByUniversityNameAndMajor(college,major);

        List<JobInfo> needList = new ArrayList<>();

        for (JobInfo info : infoList ){
            if  (info.getGraduateYear().equals(year)){
                needList.add(info);
            }
        }
        DesCityDTO desCityDTO = new DesCityDTO(year,college,major);
        desCityDTO.setTotal(needList.size());
        Map<String,Integer> map = new ConcurrentHashMap<>();
        Integer count = 0;
        for (JobInfo info:needList){
            String city = info.getCity();
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
    public ContiOrWorkDTO getChoice(Integer year, String college, String major) {
        return null;
    }

    @Override
    public DesInstitutionDTO getInstitution(Integer year, String college, String major, Integer which) {
        return null;
    }
}
