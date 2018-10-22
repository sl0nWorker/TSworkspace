package com.inc.slon.dao;

import com.inc.slon.model.CountryMap;

public interface CountryMapDao {
    void add(CountryMap countryMap);
    CountryMap getCountryMap();
}
