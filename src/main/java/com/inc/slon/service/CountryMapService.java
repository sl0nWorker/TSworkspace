package com.inc.slon.service;

import com.inc.slon.model.CountryMap;
import com.inc.slon.model.Route;

import java.util.List;

public interface CountryMapService {
    void add(CountryMap countryMap);
    List<CountryMap> countryMapList();
    int timeForRouteList(List <Route> routeList);
}
