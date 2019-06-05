package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.CityDao;
import com.daxueshi.sqlwork.domain.City;
import com.daxueshi.sqlwork.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-05 -23:31
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;
    @Override
    public List<City> findByProvinceId(Integer provinceId) {
        return cityDao.findByProvinceId(provinceId);
    }
}
