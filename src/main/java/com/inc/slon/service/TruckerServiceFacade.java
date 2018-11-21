package com.inc.slon.service;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

public interface TruckerServiceFacade {

    void putMap(ModelMap map);

    void addTrucker(String firstName, String lastName, int personalNumber, String cityId);

    void deleteTruckers(HttpServletRequest request);

    void editTrucker(String firstName, String lastName, Integer personalNumber, Integer workHours, String statusId, String cityId, Long truckerId);
}
