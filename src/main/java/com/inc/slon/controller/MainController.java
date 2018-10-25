package com.inc.slon.controller;

import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;
import com.inc.slon.model.Truck;
import com.inc.slon.service.CityService;
import com.inc.slon.service.CountryMapService;
import com.inc.slon.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    private TruckService truckService;
    @Autowired
    private CountryMapService countryMapService;
    @Autowired
    private CityService cityService;

    private static final String INDEX_PAGE = "home/home";
    private static final String TRUCKS_PAGE = "trucks";

    @RequestMapping("/home")
    public String showIndexPage(ModelMap map) {
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

    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String showTrucksPage(ModelMap map) {
        map.addAttribute("trucksList", truckService.truckList());
        return TRUCKS_PAGE;
    }

    @RequestMapping(value = "/trucks", method = RequestMethod.POST)
    public String deleteTrucks(ModelMap map, HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            truckService.removeAllById(ids);
        }
        map.addAttribute("trucksList", truckService.truckList());
        return TRUCKS_PAGE;
    }
}
