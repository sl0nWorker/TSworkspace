package com.inc.slon.dao;

import com.inc.slon.model.City;

import java.util.List;

public interface CityDao {
    void add(City city);

    List<City> cityList();

    City findById(String id);
}
