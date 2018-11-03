package com.inc.slon.controller;

import com.inc.slon.model.Freight;
import com.inc.slon.model.FreightStatus;
import com.inc.slon.model.Order;
import com.inc.slon.model.Route;
import com.inc.slon.service.CityService;
import com.inc.slon.service.FreightStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateRouteListController {
    public final static String CREATE_ROUTE_PAGE = "createRouteList";

    @Autowired
    Logger log;

    @Autowired
    CityService cityService;

    @Autowired
    FreightStatusService freightStatusService;

    @RequestMapping(value = {"/createRouteList"}, method = RequestMethod.GET)
    public String showCreateOrderPage(ModelMap map, HttpSession httpSession) {
        log.info("/createRouteList, get) start");
        // TODO: add statuse for testing
        List<FreightStatus> freightStatusList = freightStatusService.statusList();
        log.info("freightStatusList: " + freightStatusList);
        if (freightStatusList.size() == 0) {
            log.info("freightStatusList: " + freightStatusList);
            FreightStatus freightStatusPrepared = new FreightStatus();
            freightStatusPrepared.setStatus("Prepared");
            FreightStatus freightStatusShipped = new FreightStatus();
            freightStatusShipped.setStatus("Shipped");
            FreightStatus freightStatusDelivered = new FreightStatus();
            freightStatusDelivered.setStatus("Delivered");
            freightStatusService.add(freightStatusPrepared);
            freightStatusService.add(freightStatusShipped);
            freightStatusService.add(freightStatusDelivered);
            log.info("freightStatusList after 3 persist: " + freightStatusService.statusList());
        }
        if (httpSession.getAttribute("routeList") == null) {
            List<Route> routeList = new ArrayList<>();
            httpSession.setAttribute("routeList", routeList);
            map.addAttribute("routeList", routeList);
            map.addAttribute("citiesList", cityService.cityList());
        } else {
            // TODO: add type checks
            List<Route> routeList = (List<Route>) httpSession.getAttribute("routeList");
            map.addAttribute("routeList", routeList);
            log.info("routeList!=null: size of list = " + routeList.size());
            map.addAttribute("citiesList", cityService.cityList());
        }
        log.info("/createRouteList, get) end, return CREATE_ROUTE_PAGE");
        return CREATE_ROUTE_PAGE;
    }

    @RequestMapping(value = {"/createRouteList"}, method = RequestMethod.POST)
    public ModelAndView addRouteInOrder(ModelMap map, HttpSession httpSession,
                                        @RequestParam(value = "city") String cityId,
                                        @RequestParam(value = "freightName") String freightName,
                                        @RequestParam(value = "freightWeight") Integer freightWeight,
                                        @RequestParam(value = "loading") String loading) {
        log.info("/createRouteList, post) start");
        //TODO: add checking that loading is ahead of unloading. If unloading , check existing way froma A to B
        // TODO: add type checks
        List<Route> routeList = (List<Route>) httpSession.getAttribute("routeList");
        log.info("/createRouteList,post getOrder");

        if (routeList!= null && routeList.size() != 0) {
            log.info("/createRouteList,post getRouteList");
            log.info("route: " + routeList.get(0));
        }
        Route route = new Route();
        //TODO: add checking cityService.findById
        log.info("Find cityID");
        route.setCity(cityService.findById(cityId));
        //TODO: persist route after freight
        Freight freight = new Freight();
        freight.setName(freightName);
        freight.setWeight(freightWeight);
        log.info("Find freightStatus");
        log.info("List status: " + freightStatusService.statusList());
        log.info("FINDBYSTATUSNAME: " + freightStatusService.findByStatusName("Prepared"));
        freight.setFreightStatus(freightStatusService.findByStatusName("Prepared"));
        log.info("ERROR here");
        route.setFreight(freight);

        if (loading.equals("Loading")) {
            route.setUnloading(false);
        } else {
            route.setUnloading(true);
        }
        routeList.add(route);
        log.info("set route: " + routeList.size());
        httpSession.setAttribute("routeList", routeList);
        map.addAttribute("routeList", routeList);


        log.info("/createRouteList, post) end, return CREATE_ROUTE_PAGE");
        return new ModelAndView("redirect:" + CREATE_ROUTE_PAGE);
    }

    @RequestMapping(value = {"/createRouteList/deleteRoute"}, method = RequestMethod.POST)
    public ModelAndView deleteRouteInList(ModelMap map, HttpSession httpSession,
                                          @RequestParam(value = "id") Integer[] ids) {

        log.info("(/createRouteList/deleteRoute, post) start");
        // TODO: add type checks
        List<Route> routeList = (List<Route>) httpSession.getAttribute("routeList");
        log.info("RouteList before remove");
        for (Route route : routeList) {
            log.info(route);
        }
        // after removing an element in a list, indexing will be shifted by 1
        int offset = 1;
        for (int i = 0; i < ids.length; i++) {
            log.info("remove ids: " + (ids[i] - offset));
            routeList.remove(ids[i] - offset++);
        }
        log.info("RouteList after remove");
        for (Route route : routeList) {
            log.info(route);
        }


        httpSession.setAttribute("routeList", routeList);
        map.addAttribute("routeList", routeList);
        log.info("(/createRouteList/deleteRoute, post) end");
        return new ModelAndView("redirect:/createRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList"}, method = RequestMethod.POST)
    public ModelAndView saveRouteList(ModelMap map, HttpSession httpSession) {

        log.info("(/createRouteList/saveRouteList, post) start");
        // TODO: add checks
        List<Route> savedRouteList = (List<Route>)httpSession.getAttribute("routeList");
        httpSession.setAttribute("savedRouteList",savedRouteList);
        map.addAttribute("savedRouteList",savedRouteList);
        log.info("(/createRouteList/saveRouteList, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList"}, method = RequestMethod.GET)
    public String showRouteList(ModelMap map, HttpSession httpSession) {

        log.info("(/createRouteList/saveRouteList, get) start");


        log.info("(/createRouteList/saveRouteList, get) end");
        return "savedRouteList";
    }
}


