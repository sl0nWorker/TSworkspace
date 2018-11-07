package com.inc.slon.controller;

import com.inc.slon.model.Order;
import com.inc.slon.model.Route;
import com.inc.slon.model.Trucker;
import com.inc.slon.service.OrderService;
import com.inc.slon.service.TruckerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TruckerUiController {
    private static final String TRUCKER_UI_PAGE = "truckerUi";
    @Autowired
    private Logger log;
    @Autowired
    private TruckerService truckerService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {"/truckerUi"}, method = RequestMethod.GET)
    public String showTruckerList(ModelMap map) {
        final String path = "(/truckerUi, get) ";
        log.info(path + "start");
        List<Trucker> truckerList = truckerService.truckerList();
        map.addAttribute("truckerList", truckerList);

        log.info(path + "end");
        return TRUCKER_UI_PAGE;
    }

    @RequestMapping(value = {"/truckerUi/trucker"}, method = RequestMethod.POST)
    public ModelAndView showTruckerInfo(ModelMap map, HttpServletRequest request,
                                        @RequestParam(value = "truckerId") Long truckerId) {
        final String path = "(/truckerUi/trucker, post) ";
        log.info(path + "start");

        Trucker trucker = truckerService.findById(truckerId);
        map.addAttribute("trucker", trucker);


        Long truckId = null;
        if (trucker.getTruck() != null) {
            truckId = trucker.getTruck().getId();
            // add cotrucker in map
            Order order = orderService.findByTruckId(truckId);
            if (order != null) {
                List<Trucker> truckerList = order.getTruckerList();
                for (Trucker t : truckerList) {
                    if (t.getId() != truckerId) {
                        map.addAttribute("cotrucker", t);
                    }
                }
                // add routeList
                List<Route> routeList = order.getRouteList();
                map.addAttribute("routeList", routeList);
            }
        }


        log.info(path + "end");
        return new ModelAndView("truckerInfo");
    }
}
