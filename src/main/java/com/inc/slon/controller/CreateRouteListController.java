package com.inc.slon.controller;

import com.inc.slon.model.*;
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

    @Autowired
    TruckService truckService;

    @Autowired
    TruckerService truckerService;

    @Autowired
    RouteService routeService;

    @Autowired
    OrderService orderService;

    @Autowired
    FreightService freightService;

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

        if (routeList != null && routeList.size() != 0) {
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

    //TODO: add checks for changing status (someone else assigned truck or truckers)
    @RequestMapping(value = {"/createRouteList/saveRouteList"}, method = RequestMethod.POST)
    public ModelAndView saveRouteList(ModelMap map, HttpSession httpSession) {
        log.info("(/createRouteList/saveRouteList, post) start");
        //type checks
        List<Route> savedRouteList = (List<Route>) httpSession.getAttribute("routeList");
        httpSession.setAttribute("savedRouteList", savedRouteList);


        List<Truck> truckList = truckService.truckList();
        List<Truck> freeTruckList = new ArrayList<>();
        // Free trucks, orderId = null.
        // TODO: add checks
        // working = true
        // weight is ok, with loading/unloading
        // not in another order
        for (Truck truck : truckList) {
            if (truck.getOrder() == null) {
                freeTruckList.add(truck);
            }
        }
        //change to map
        httpSession.setAttribute("freeTruckList", freeTruckList);

        log.info("(/createRouteList/saveRouteList, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList/assignTruck"}, method = RequestMethod.POST)
    public ModelAndView assignTruck(ModelMap map, HttpSession httpSession,
                                    @RequestParam(value = "truckId") String truckId) {


        log.info("(/createRouteList/saveRouteList/assignTruck, post) start");
        // TODO: add checks

        //assign the truck
        if (httpSession.getAttribute("assignedTruckId") == null) {
            if (truckId != null) {
                httpSession.setAttribute("assignedTruckId", truckId);
                Truck assignedTruck = truckService.findById(truckId);
                httpSession.setAttribute("assignedTruck", assignedTruck);

                //send appropriate truckerList
                List<Trucker> truckerList = truckerService.truckerList();
                // check time limit = 176 hours
                // trucker is free
                // same city as the assignedTruck
                // check(truckerList)
                List<Trucker> checkedTruckerList = truckerList;
                httpSession.setAttribute("checkedTruckerList", checkedTruckerList);
                //set savedTruckcerList  (for savedTruckerList.size = 0, in jsp check)
                List<Trucker> savedTruckerList = new ArrayList<>();
                httpSession.setAttribute("savedTruckerList", savedTruckerList);
            }
        }

        log.info("(/createRouteList/saveRouteList/assignTruck, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList/assignTrucker"}, method = RequestMethod.POST)
    public ModelAndView assignTrucker(ModelMap map, HttpSession httpSession,
                                      @RequestParam(value = "truckId", required = false) Long truckId,
                                      @RequestParam(value = "truckerId", required = false) Long truckerId) {


        log.info("(/createRouteList/saveRouteList/assignTrucker, post) start");
        // TODO: add checks

        if (truckerId == null) {
            log.error("(/createRouteList/saveRouteList/assignTrucker, post) truckId = null");
            // throw exception
        }

        // add tpye checks
        List<Trucker> savedTruckerList = (List<Trucker>) httpSession.getAttribute("savedTruckerList");
        Trucker truckerAdd = truckerService.findById(truckerId);
        savedTruckerList.add(truckerAdd);
        httpSession.setAttribute("savedTruckerList", savedTruckerList);

        //send appropriate truckerList

        //add type checks
        String assignedTruckId = (String) httpSession.getAttribute("assignedTruckId");
        if (savedTruckerList.size() == 1) {
            List<Trucker> truckerList = truckerService.truckerList();
            // check time limit = 176 hours
            // trucker is free
            // same city as the assignedTruck
            // check(truckerList)
            // DELETE trucker with truckerId in checkedTruckerList
            List<Trucker> checkedTruckerList = truckerList;
            log.info("chekedTruckerList:" + checkedTruckerList + " size: " + checkedTruckerList.size());
            httpSession.setAttribute("checkedTruckerList", checkedTruckerList);
        }

        log.info("(//createRouteList/saveRouteList/assignTrucker, post) end");
        return new ModelAndView("redirect:/createRouteList/saveRouteList");
    }

    @RequestMapping(value = {"/createRouteList/saveRouteList/assignOrder"}, method = RequestMethod.POST)
    public ModelAndView assignOrder(ModelMap map, HttpSession httpSession) {

        log.info("(/createRouteList/saveRouteList/assignOrder, post) start");
        Order order = new Order();
        order.setReady(false);
        Truck truck = (Truck) httpSession.getAttribute("assignedTruck");
        order.setTruck(truck);
        List<Route> routeList = (List<Route>) httpSession.getAttribute("savedRouteList");
        order.setRouteList(routeList);
        List<Trucker> truckerList = (List<Trucker>) httpSession.getAttribute("savedTruckerList");
        order.setTruckerList(truckerList);
        //pre persisting checks (status is still the same)
        //for status truck, for status trucker.
        Freight freightToPersist;
        for (Route route : routeList) {
            freightToPersist = route.getFreight();
            freightService.add(freightToPersist);
            routeService.add(route);
        }
        log.info("BEFORE ADDING ORDER");
        orderService.add(order);
        log.info("AFTER ADDING ORDER");

        // TODO: clean httpSession
        httpSession.setAttribute("routeList", null);
        httpSession.setAttribute("savedRouteList", null);
        httpSession.setAttribute("savedTruckerList", null);
        httpSession.setAttribute("assignedTruck", null);
        httpSession.setAttribute("assignedTruckId", null);
        httpSession.setAttribute("checkedTruckerList", null);

        List<Order> orderList = (List<Order>) httpSession.getAttribute("orderList");
        if (orderList == null) {
            orderList = new ArrayList<>();
            orderList.add(order);
        } else {
            orderList.add(order);
        }
        httpSession.setAttribute("orderList", orderList);

        log.info("(/createRouteList/saveRouteList/assignOrder, post) end");
        return new ModelAndView("redirect:/activeOrders");
    }


    @RequestMapping(value = {"/createRouteList/saveRouteList"}, method = RequestMethod.GET)
    public String showRouteList(ModelMap map, HttpSession httpSession) {

        log.info("(/createRouteList/saveRouteList, get) start");


        log.info("(/createRouteList/saveRouteList, get) end");
        return "savedRouteList";
    }
}


