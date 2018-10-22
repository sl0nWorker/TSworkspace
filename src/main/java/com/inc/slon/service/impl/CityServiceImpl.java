package com.inc.slon.service.impl;

import com.inc.slon.dao.CityDao;
import com.inc.slon.model.City;
import com.inc.slon.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityDao cityDao;

    @Transactional
    @Override
    public void add(City city) {
        cityDao.add(city);

    }
    @Transactional(readOnly = true)
    @Override
    public List<City> cityList() {
        return cityDao.cityList();
    }
}
