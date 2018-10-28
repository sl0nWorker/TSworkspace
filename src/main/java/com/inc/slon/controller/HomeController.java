package com.inc.slon.controller;

import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;
import com.inc.slon.model.Truck;
import com.inc.slon.service.CityService;
import com.inc.slon.service.CountryMapService;
import com.inc.slon.service.TruckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private TruckService truckService;
    @Autowired
    private CountryMapService countryMapService;
    @Autowired
    private CityService cityService;

    private static final String INDEX_PAGE = "home/home";
    private static final String TEST_PAGE = "TestJsp";
    @Autowired
    private Logger log;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String showIndexPage(ModelMap map) {
        log.info("I`m in homePage method=get");
        return INDEX_PAGE;
    }

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String showTestPage(){
            return TEST_PAGE;
    }

}
