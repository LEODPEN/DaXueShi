package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.City;

import java.util.List;

/**
 * @author onion
 * @date 2019-06-05 -23:31
 */
public interface CityService {
    List<City> findByProvinceId(Integer provinceId);
}
