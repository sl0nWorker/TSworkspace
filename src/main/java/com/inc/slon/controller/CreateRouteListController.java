package com.inc.slon.controller;


import com.inc.slon.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class CreateRouteListController {
    private final static String CREATE_ROUTE_PAGE = "createRouteList";

    @Autowired
    Logger log;

    @Autowired
    RouteServiceFacade routeServiceFacade;

    @RequestMapping(value = {"/createRouteList"}, method = RequestMethod.GET)
    public String showCreateOrderPage(ModelMap map, HttpSession httpSession) {
        log.info("/createRouteList, get) start");
        routeServiceFacade.putModelMapAndHttpSession(map,httpSession);
        log.info("/createRouteList, get) end, return CREATE_ROUTE_PAGE");
        return CREATE_ROUTE_PAGE;
    }

    @RequestMapping(value = {"/createRouteList"}, method = RequestMethod.POST)
    public ModelAndView addRouteInOrder(ModelMap map, HttpSession httpSession,
                                        @RequestParam(value = "city") String cityId,
                                        @RequestParam(value = "freightNumber") Integer freightNumber,
                                        @RequestParam(value = "freightName") String freightName,
                                        @RequestParam(value = "freightWeight") Integer freightWeight,
                                        @RequestParam(value = "loading") String loading) {
        log.info("/createRouteList, post) start");
        routeServiceFacade.addRoute(map,httpSession,cityId,freightNumber,freightName,freightWeight,loading);
        log.info("/createRouteList, post) end, return CREATE_ROUTE_PAGE");
        return new ModelAndView("redirect:" + CREATE_ROUTE_PAGE);
    }

    @RequestMapping(value = {"/createRouteList/deleteRoute"}, method = RequestMethod.POST)
    public ModelAndView deleteRouteInList(ModelMap map, HttpSession httpSession, @RequestParam(value = "id") Integer[] ids) {

        log.info("(/createRouteList/deleteRoute, post) start");
        routeServiceFacade.deleteRoutes(map,httpSession,ids);
        log.info("(/createRouteList/deleteRoute, post) end");
        return new ModelAndView("redirect:/createRouteList");
    }


    @RequestMapping(value = {"/createRouteList/saveRouteList"}, method = RequestMethod.POST)
    public ModelAndView saveRouteList(ModelMap map, HttpSession httpSession) {
        log.info("(/createRouteList/saveRouteList, post) start");
        routeServiceFacade.saveRouteList(map, httpSession);
        log.info("(/createRouteList/saveRouteList, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList/assignTruck"}, method = RequestMethod.POST)
    public ModelAndView assignTruck(HttpSession httpSession, @RequestParam(value = "truckId") String truckId) {
        log.info("(/createRouteList/saveRouteList/assignTruck, post) start");
        routeServiceFacade.assignTruck(httpSession,truckId);
        log.info("(/createRouteList/saveRouteList/assignTruck, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList/assignTrucker"}, method = RequestMethod.POST)
    public ModelAndView assignTrucker(HttpSession httpSession, @RequestParam(value = "truckerId", required = false) Long truckerId) {
        log.info("(/createRouteList/saveRouteList/assignTrucker, post) start");
        routeServiceFacade.assignTrucker(httpSession,truckerId);
        log.info("(//createRouteList/saveRouteList/assignTrucker, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList/assignOrder"}, method = RequestMethod.POST)
    public ModelAndView assignOrder(HttpSession httpSession) {
        log.info("(/createRouteList/saveRouteList/assignOrder, post) start");
        routeServiceFacade.createOrder(httpSession);
        log.info("(/createRouteList/saveRouteList/assignOrder, post) end");
        return new ModelAndView("redirect:/activeOrders");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList"}, method = RequestMethod.GET)
    public String showRouteList() {
        log.info("(/createRouteList/saveRouteList, get) start");
        log.info("(/createRouteList/saveRouteList, get) end");
        return "savedRouteList";
    }
}


