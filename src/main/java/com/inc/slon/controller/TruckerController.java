package com.inc.slon.controller;

import com.inc.slon.service.TruckerServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TruckerController {
    private static final String TRUCKERS_PAGE = "/truckers";

    @Autowired
    private TruckerServiceFacade truckerServiceFacade;

    @Autowired
    private Logger log;

    @RequestMapping(value = "/truckers", method = RequestMethod.GET)
    public String showTruckersPage(ModelMap map) {
        log.info("(/truckers, get) start");
        truckerServiceFacade.putMap(map);
        log.info("(/truckers, get) end, return TRUCKERS_PAGE");
        return TRUCKERS_PAGE;
    }

    @RequestMapping(value = "/truckersAdd", method = RequestMethod.POST)
    public ModelAndView addTrucker(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("personalNumber") int personalNumber,
                                 @RequestParam(value = "city") String cityId) {
        log.info("(/truckersAdd, post) start");
        truckerServiceFacade.addTrucker(firstName,lastName,personalNumber,cityId);
        log.info("name: " + firstName + ", lastName: " + lastName + ", personalNumber: " + personalNumber + ", workHours: " + 0);
        log.info("(/truckersAdd, post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKERS_PAGE);
    }

    @RequestMapping(value = "/truckersDelete", method = RequestMethod.POST)
    public ModelAndView deleteTruckers(HttpServletRequest request) {
        log.info("(/truckersDelete, post) start");
        truckerServiceFacade.deleteTruckers(request);
        log.info("(/truckersDelete, post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKERS_PAGE);
    }

    @RequestMapping(value = "/truckersEdit", method = RequestMethod.POST)
    public ModelAndView editTrucker(@RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "lastName", required = false) String lastName,
                                  @RequestParam(value = "personalNumber", required = false) Integer personalNumber,
                                  @RequestParam(value = "workHours", required = false) Integer workHours,
                                  @RequestParam(value = "statusId", required = false) String statusId,
                                  @RequestParam(value = "city", required = false) String cityId,
                                  @RequestParam(value = "truckerId") Long truckerId) {
        log.info("(/truckersEdit, post) start");
        truckerServiceFacade.editTrucker(firstName,lastName,personalNumber,workHours,statusId,cityId,truckerId);
        log.info("(/truckersEdit, post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKERS_PAGE);
    }
}
