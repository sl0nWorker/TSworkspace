package com.inc.slon.service.impl;

import com.inc.slon.dao.CountryMapDao;
import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;
import com.inc.slon.model.Route;
import com.inc.slon.service.CountryMapService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryMapServiceImpl implements CountryMapService {
    @Autowired
    CountryMapDao countryMapDao;

    @Autowired
    Logger log;

    @Transactional
    @Override
    public void add(CountryMap countryMap) {
        countryMapDao.add(countryMap);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CountryMap> countryMapList() {
        return countryMapDao.countryMapList();
    }

    @Transactional(readOnly = true)
    @Override
    public int timeForRouteList(List<Route> routeList) {
        int timeForRouteList = 0;
        int sumOfDistances = 0;
        if(routeList == null || routeList.size() < 2){
            return 0;
        }
        for (int i = 0; i < routeList.size() - 1; i++) {
            City cityFrom = routeList.get(i).getCity();
            log.info("ContryMapSerivce: cityFrom " + cityFrom);
            City cityTo = routeList.get(i+1).getCity();
            log.info("ContryMapSerivce: cityTo " + cityTo);
            if (cityFrom.getId() != cityTo.getId()){
                int distance = countryMapDao.distance(cityFrom,cityTo);
                log.info("ContryMapService: distnace - " + distance);
                sumOfDistances = sumOfDistances + distance;
            }
        }
        timeForRouteList = sumOfDistances / 70;
        log.info("ContryMapSerivce: timeForRouteList = " + timeForRouteList);
        return timeForRouteList;
    }
}
