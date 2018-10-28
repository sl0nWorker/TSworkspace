package com.inc.slon.service;

import com.inc.slon.model.City;

import java.util.List;

public interface CityService {
    void add(City city);
    List<City> cityList();
    City findById(String id);
}
