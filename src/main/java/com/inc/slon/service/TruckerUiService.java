package com.inc.slon.service;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public interface TruckerUiService {
    void putModelMap(ModelMap map);

    void showTruckerInfo(ModelMap map, Long truckerId);

    void changeTruckerShift(Long truckerId, String shiftStatus);

    void changeTruckerStatus(Long truckerId, String changeStatus);

    void changeFreightStatusInTruckerOrder(Long routeId, Long freightId, Boolean unloading, Long truckerId);

    void completeTruckerOrder(Long truckerId, Long orderId, HttpSession httpSession);
}
