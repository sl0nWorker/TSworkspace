package com.inc.slon.controller;

import com.inc.slon.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TruckController {
    private static final String TRUCKS_PAGE = "trucks";
    @Autowired
    private TruckService truckService;

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
