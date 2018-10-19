package com.inc.slon.controller;

import com.inc.slon.model.Truck;
import com.inc.slon.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private TruckService truckService;
    private static final String INDEX_PAGE = "home";

    @RequestMapping("/home")
    public String showIndexPage(ModelMap map) {
        Truck truck = new Truck();
        truck.setGoodCondition(true);
        truck.setName("first");
        truckService.add(truck);
        map.addAttribute("trucksList", truckService.listTrucks());
        return INDEX_PAGE;
    }
}
