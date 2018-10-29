package com.inc.slon.controller;


import com.inc.slon.model.City;
import com.inc.slon.model.Truck;
import com.inc.slon.model.Trucker;
import com.inc.slon.model.TruckerStatus;
import com.inc.slon.service.CityService;
import com.inc.slon.service.TruckerService;
import com.inc.slon.service.TruckerStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class TruckerController {
    private static final String TRUCKERS_PAGE = "/truckers";
    //TODO: add service that use truckerService and cityService, don`t use more then one service in controller
    @Autowired
    private TruckerService truckerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TruckerStatusService truckerStatusService;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/truckers", method = RequestMethod.GET)
    public String showTruckersPage(ModelMap map) {
        //TODO: add warper service to use more then one serivce
        //TODO: add checking unused trucks to add in trucksList (select list in modal addTrucker)
        map.addAttribute("truckersList", truckerService.truckerList());
        map.addAttribute("citiesList", cityService.cityList());
        map.addAttribute("truckerStatusList", truckerStatusService.truckerStatusList());
        return TRUCKERS_PAGE;
    }

    //stopper
    @RequestMapping(value = "/truckersAdd", method = RequestMethod.POST)
    //TODO: add warper service to use more then one serivce
    public ModelAndView addTruck(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("personalNumber") int personalNumber,
                                 @RequestParam("workHours") int workHours,
                                 @RequestParam(value = "city") String cityId,
                                 //TODO: add truck required = true, selected list (unused trucks)
                                 ModelMap map) {

        log.info("name: " + firstName + ", lastName: " + lastName + ", personalNumber: " + personalNumber + ", workHours: " + workHours);
        //TODO: add constructor in Trucker
        //Trucker truckerAdd = new Trucker(firstName, lastName, personalNumber, workHours, cityAdd);
        Trucker truckerAdd = new Trucker();
        log.info("try to set firstName");
        truckerAdd.setFirstName(firstName);
        truckerAdd.setLastName(lastName);
        truckerAdd.setPersonalNumber(personalNumber);
        truckerAdd.setWorkHours(workHours);
        TruckerStatus truckerStatus = truckerStatusService.findById("1");
        if (truckerStatus.getStatus().equals("FREE")) {
            truckerAdd.setStatus(truckerStatus);
        } else {
            log.error("(/truckersAdd, post) TRUCKER_STATUS with id:1  must be FREE");
        }
        if (cityId != null && !cityId.equals("")) {
            City cityAdd = cityService.findById(cityId);
            truckerAdd.setCity(cityAdd);
        } else {
            log.error("(/truckersAdd, post) RequestParam city is invalid");
        }

        log.info("Try add truck with city");
        truckerService.add(truckerAdd);
        log.info("add truck with city was OK");

        return new ModelAndView("redirect:" + TRUCKERS_PAGE);
    }

    @RequestMapping(value = "/truckersDelete", method = RequestMethod.POST)
    public ModelAndView deleteTruckers(ModelMap map, HttpServletRequest request) {
        log.info("I`m in /truckersDelete post");
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            log.info("Try to remove by Id");
            log.info(Arrays.toString(ids));
            truckerService.removeAllById(ids);
            log.info("removing by id was OK");
        } else {
            log.error("(/truckersDelete, post) String[] ids = null or ids.length <=0 ");
        }
        return new ModelAndView("redirect:" + TRUCKERS_PAGE);
    }
}
