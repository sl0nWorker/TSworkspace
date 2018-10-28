package com.inc.slon.controller;


import com.inc.slon.model.City;
import com.inc.slon.model.Truck;
import com.inc.slon.model.Trucker;
import com.inc.slon.service.CityService;
import com.inc.slon.service.TruckerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TruckerController {
    private static final String TRUCKERS_PAGE = "truckers";
    //TODO: add service that use truckerService and cityService, don`t use more then one service in controller
    @Autowired
    private TruckerService truckerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/truckers", method = RequestMethod.GET)
    public String showTruckersPage(ModelMap map) {
        //TODO: add warper service to use more then one serivce
        map.addAttribute("truckersList", truckerService.truckerList());
        return TRUCKERS_PAGE;
    }

    //stopper
    @RequestMapping(value = "/truckersAdd", method = RequestMethod.POST)
    public String addTruck(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("personalNumber") int personalNumber,
                           @RequestParam("workHours") int workHours,
                           //TODO: city required = true, false for testing
                           @RequestParam(value = "city",required = false) String cityId,
                           ModelMap map) {

        log.info("name: " + firstName +", lastName: " + lastName +", personalNumber: " + personalNumber +", workHours: " + workHours);
         if(cityId != null && !cityId.equals("")){
             City cityAdd = cityService.findById(cityId);
         }
        //TODO: add constructor in Trucker
        //Trucker truckerAdd = new Trucker(firstName, lastName, personalNumber, workHours, cityAdd);
        Trucker truckerAdd = new Trucker();
        log.info("try to set firstName");
        truckerAdd.setFirstName(firstName);
        truckerAdd.setLastName(lastName);
        truckerAdd.setPersonalNumber(personalNumber);
        truckerAdd.setWorkHours(workHours);
        log.info("Try add truck with city");
        truckerService.add(truckerAdd);
        log.info("add truck with city was OK");
        //TODO: add warper service to use more then one serivce
        map.addAttribute("truckersList", truckerService.truckerList());
        map.addAttribute("citiesList", cityService.cityList());
        //TODO: redirect to /trucks, because f5 add the same truck again
        return TRUCKERS_PAGE;
    }
}
