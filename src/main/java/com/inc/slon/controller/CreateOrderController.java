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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateOrderController {
    public final static String CREATE_ORDER_PAGE = "createOrder";

    @Autowired
    Logger log;

    @Autowired
    CityService cityService;

    @Autowired
    FreightStatusService freightStatusService;

    @RequestMapping(value = {"/createOrder"}, method = RequestMethod.GET)
    public String showCreateOrderPage(ModelMap map, HttpSession httpSession) {
        log.info("/createOrder, get) start");
        // TODO: add statuse for testing
        List<FreightStatus> freightStatusList = freightStatusService.statusList();
        log.info("freightStatusList: " + freightStatusList);
        if (freightStatusList.size() == 0){
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
        if (httpSession.getAttribute("order") == null) {
            Order order = new Order();
            httpSession.setAttribute("order", order);
            map.addAttribute("order", order);
            map.addAttribute("citiesList",cityService.cityList());
        } else {
            Order order = (Order) httpSession.getAttribute("order");
            map.addAttribute("order",order);
            log.info("order!=null: size of list = " + order.getRouteList().size());
            map.addAttribute("citiesList",cityService.cityList());
        }
        log.info("/createOrder, get) end, return CREATE_ORDER_PAGE");
        return CREATE_ORDER_PAGE;
    }

    @RequestMapping(value = {"/createOrder"}, method = RequestMethod.POST)
    public ModelAndView addRouteInOrder(ModelMap map, HttpSession httpSession,
                                        @RequestParam(value = "city") String cityId,
                                        @RequestParam(value = "freightName") String freightName,
                                        @RequestParam(value = "freightWeight") Integer freightWeight,
                                        @RequestParam(value = "loading") String loading) {
        log.info("/createOrder, post) start");
        Order order = (Order) httpSession.getAttribute("order");

        if (order != null) {
            if (order.getRouteList() != null){
                log.info("route: " + order.getRouteList().get(0));
            }
            List <Route> routeList = order.getRouteList();
            if (routeList == null){
                routeList = new ArrayList<>();
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
            log.info("List status: "+freightStatusService.statusList());
            log.info("FINDBYSTATUSNAME: "+ freightStatusService.findByStatusName("Prepared"));
            freight.setFreightStatus(freightStatusService.findByStatusName("Prepared"));
            log.info("ERROR here");
            route.setFreight(freight);

            if (loading.equals("Loading")){
                route.setUnloading(false);
            } else {
                route.setUnloading(true);
            }
            routeList.add(route);
            order.setRouteList(routeList);
            log.info("set order: " + order.getRouteList().size());
            httpSession.setAttribute("order",order);
            map.addAttribute("order",order);
        }




        log.info("/createOrder, post) end, return CREATE_ORDER_PAGE");
        return new ModelAndView("redirect:" + CREATE_ORDER_PAGE);
    }
}
