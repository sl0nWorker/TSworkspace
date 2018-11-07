package com.inc.slon.dao;

import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;

import java.util.List;

public interface CountryMapDao {
    void add(CountryMap countryMap);

    List<CountryMap> countryMapList();

    int distance(City cityFrom, City cityTo);
}
