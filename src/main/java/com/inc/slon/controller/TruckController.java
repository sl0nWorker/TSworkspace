package com.inc.slon.controller;

import com.inc.slon.service.TruckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class TruckController {
    private static final String TRUCKS_PAGE = "trucks";
    @Autowired
    private TruckService truckService;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String showTrucksPage(ModelMap map) {
        map.addAttribute("trucksList", truckService.truckList());
        return TRUCKS_PAGE;
    }

    @RequestMapping(value = "/trucksDelete", method = RequestMethod.POST)
    public String deleteTrucks(ModelMap map, HttpServletRequest request) {
        log.info("I`m in /trucks post");
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            log.info("Try to remove by Id");
            log.info(Arrays.toString(ids));
            truckService.removeAllById(ids);
            log.info("removing by id was OK");
        }
        log.info("add to model trucklist");
        map.addAttribute("trucksList", truckService.truckList());
        log.info("return trucks page");
        return TRUCKS_PAGE;
    }
    //stopper
    @RequestMapping(value = "/trucksAdd", method = RequestMethod.POST)
    public String addTruck(ModelMap map, HttpServletRequest request) {
        map.addAttribute("trucksList", truckService.truckList());
        return TRUCKS_PAGE;
    }
}
