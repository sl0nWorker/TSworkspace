package com.inc.slon.controller;

import com.inc.slon.service.TruckServiceFacade;
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
public class TruckController {
    private static final String TRUCKS_PAGE = "/trucks";
    @Autowired
    private TruckServiceFacade truckServiceFacade;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String showTrucksPage(ModelMap map) {
        log.info("(/trucks,get) start");
        truckServiceFacade.putModelMap(map);
        log.info("(/trucks,get) end, return TRUCKS_PAGE");
        return TRUCKS_PAGE;
    }

    @RequestMapping(value = "/trucksDelete", method = RequestMethod.POST)
    public ModelAndView deleteTrucks(HttpServletRequest request) {
        log.info("(/trucksDelete, post), start");
        truckServiceFacade.deleteTruck(request);
        log.info("(/trucksDelete, post), end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }

    @RequestMapping(value = "/trucksAdd", method = RequestMethod.POST)
    public ModelAndView addTruck(@RequestParam("regNumber") String regNumber,
                                 @RequestParam("workShift") Integer workShift,
                                 @RequestParam("loadWeight") Integer loadWeight,
                                 @RequestParam("working") Boolean working,
                                 @RequestParam("city") String cityId) {
        log.info("(/trucksAdd,post) start");
        truckServiceFacade.addTruck(regNumber,workShift,loadWeight,working,cityId);
        log.info("(/trucksAdd,post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }

    @RequestMapping(value = "/trucksEdit", method = RequestMethod.POST)
    public ModelAndView editTruck(@RequestParam(value = "regNumber", required = false) String regNumber,
                                  @RequestParam(value = "workShift", required = false) Integer workShift,
                                  @RequestParam(value = "loadWeight", required = false) Integer loadWeight,
                                  @RequestParam(value = "working", required = false) Boolean working,
                                  @RequestParam(value = "city", required = false) String city,
                                  @RequestParam(value = "idTruck", required = false) String idTruck) {
        log.info("(/trucksEdit,post) start");
        truckServiceFacade.editTruck(regNumber,workShift,loadWeight,working,city,idTruck);
        log.info("(/trucksEdit,post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }
}
