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
    @Autowired
    private Logger log;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String showIndexPage(ModelMap map) {
        log.error("WTF?___________WTF?");
        log.info("HELLO!");
        Truck truck = new Truck();
        truck.setWorking(true);
        truck.setRegNumber("K612MN");
        truckService.add(truck);

        City citySpb = new City();
        citySpb.setCityName("SPB");

        City cityMoscow = new City();
        cityMoscow.setCityName("MOSCOW");

        CountryMap russianMap = new CountryMap();

        russianMap.setCityFrom(citySpb);
        russianMap.setCityTo(cityMoscow);
        russianMap.setDistance(672);

        cityService.add(citySpb);
        cityService.add(cityMoscow);

        countryMapService.add(russianMap);

        map.addAttribute("trucksList", truckService.truckList());
        return INDEX_PAGE;
    }
}
