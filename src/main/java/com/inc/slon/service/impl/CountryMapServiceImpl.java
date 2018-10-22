package com.inc.slon.service.impl;

import com.inc.slon.dao.CountryMapDao;
import com.inc.slon.model.CountryMap;
import com.inc.slon.service.CountryMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryMapServiceImpl implements CountryMapService {
    @Autowired
    CountryMapDao countryMapDao;

    @Transactional
    @Override
    public void add(CountryMap countryMap) {
        countryMapDao.add(countryMap);
    }

    @Transactional(readOnly = true)
    @Override
    public CountryMap getContryMap() {
        return countryMapDao.getCountryMap();
    }
}
