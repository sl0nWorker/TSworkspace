package com.inc.slon.service;

import com.inc.slon.model.form.RouteForm;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public interface RouteServiceFacade {
    void putModelMapAndHttpSession(ModelMap map, HttpSession httpSession);

    void addRoute(ModelMap map, HttpSession httpSession, RouteForm routeForm);

    void deleteRoutes(ModelMap map, HttpSession httpSession, Integer[] ids);

    void saveRouteList(ModelMap map, HttpSession httpSession);

    void assignTruck(HttpSession httpSession, String truckId);

    void assignTrucker(HttpSession httpSession, Long truckerId);

    void createOrder(HttpSession httpSession);
}
