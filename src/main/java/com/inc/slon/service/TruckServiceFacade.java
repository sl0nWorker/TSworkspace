package com.inc.slon.service;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

public interface TruckServiceFacade {
    void putModelMap(ModelMap map);

    void deleteTruck(HttpServletRequest request);

    void addTruck(String regNumber, Integer workShift, Integer loadWeight, Boolean working, String cityId);

    void editTruck(String regNumber, Integer workShift, Integer loadWeight, Boolean working, String city, String idTruck);
}
