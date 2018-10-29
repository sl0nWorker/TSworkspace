package com.inc.slon.controller;

import com.inc.slon.model.City;
import com.inc.slon.model.Truck;
import com.inc.slon.service.CityService;
import com.inc.slon.service.TruckService;
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
import java.util.List;

@Controller
public class TruckController {
    private static final String TRUCKS_PAGE = "/trucks";
    @Autowired
    private TruckService truckService;
    @Autowired
    private CityService cityService;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String showTrucksPage(ModelMap map) {
        log.info("(/trucks,get) start");
        //TODO: add warper service to use more then one serivce
        map.addAttribute("trucksList", truckService.truckList());
        map.addAttribute("citiesList", cityService.cityList());
        log.info("(/trucks,get) end, return TRUCKS_PAGE");
        return TRUCKS_PAGE;
    }

    @RequestMapping(value = "/trucksDelete", method = RequestMethod.POST)
    public ModelAndView deleteTrucks(ModelMap map, HttpServletRequest request) {
        log.info("(/trucksDelete, post), start");
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            log.info("Try to remove by Id");
            log.info(Arrays.toString(ids));
            truckService.removeAllById(ids);
            log.info("removing by id was OK");
        }
        log.info("(/trucksDelete, post), end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }

    //stopper
    @RequestMapping(value = "/trucksAdd", method = RequestMethod.POST)
    //TODO: add warper service to use more then one serivce
    public ModelAndView addTruck(@RequestParam("regNumber") String regNumber,
                                 @RequestParam("workShift") int workShift,
                                 @RequestParam("loadWeight") int loadWeight,
                                 @RequestParam("working") int working,
                                 @RequestParam("city") String cityId,
                                 ModelMap map) {
        log.info("(/trucksAdd,post) start");
        Boolean workingParse = null;
        if (working == 1) {
            workingParse = new Boolean(true);
        } else {
            workingParse = new Boolean(false);
        }

        City cityAdd = cityService.findById(cityId);
        Truck truckAdd = new Truck(regNumber, workShift, loadWeight, workingParse, cityAdd);
        log.info("Try add truck with city");
        truckService.add(truckAdd);
        log.info("add truck with city was OK");

        log.info("(/trucksAdd,post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }

    //stopper
    @RequestMapping(value = "/trucksEdit", method = RequestMethod.POST)
    public ModelAndView editTruck(@RequestParam(value = "regNumber", required = false) String regNumber,
                                  @RequestParam(value = "workShift", required = false) Integer workShift,
                                  @RequestParam(value = "loadWeight", required = false) Integer loadWeight,
                                  @RequestParam(value = "working", required = false) Integer working,
                                  @RequestParam(value = "city", required = false) String city,
                                  @RequestParam(value = "idTruck", required = false) String idTruck,
                                  ModelMap map) {
        log.info("(/trucksEdit,post) start");
        log.info(idTruck + " : " + regNumber + " : " + workShift + " : " + loadWeight + " : " + working + " :cityId " + city);

        Truck updateTruck = null;
        if (idTruck != null && !idTruck.equals("")) {
            updateTruck = truckService.findById(idTruck);
        } else {
            log.error("(/trucksEdit,post) idTruck = null or empty, updating truck is impossible");
        }

        if (regNumber != null && !regNumber.equals("")) {
            updateTruck.setRegNumber(regNumber);
        }
        if (workShift != null) {
            updateTruck.setWorkShift(workShift);
        }
        if (loadWeight != null) {
            updateTruck.setLoadWeight(loadWeight);
        }
        //TODO: add select list with strings true/false
        if (working != null) {
            if (working == 0)
                updateTruck.setWorking(false);
            else
                updateTruck.setWorking(true);
        }
        //TODO: add warper service to use more then one serivce
        if (city != null && !city.equals("")) {
            log.info("changin cityName in updateTruck to: " + cityService.findById(city).getCityName());
            updateTruck.setCity(cityService.findById(city));
            log.info("updating city was ok");
        }
        truckService.update(updateTruck);

        log.info("(/trucksEdit,post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }
}
