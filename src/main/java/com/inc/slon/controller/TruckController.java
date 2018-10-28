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

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class TruckController {
    private static final String TRUCKS_PAGE = "trucks";
    @Autowired
    private TruckService truckService;
    @Autowired
    private CityService cityService;
    @Autowired
    private Logger log;

    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String showTrucksPage(ModelMap map) {
        //TODO: add warper service to use more then one serivce
        City spb = new City();
        spb.setCityName("SPB");
        City msc = new City();
        msc.setCityName("Moscow");
        City novgorod = new City();
        novgorod.setCityName("Novgorod");
        cityService.add(spb);
        cityService.add(msc);
        cityService.add(novgorod);
        map.addAttribute("trucksList", truckService.truckList());
        map.addAttribute("citiesList", cityService.cityList());
        return TRUCKS_PAGE;
    }

    @RequestMapping(value = "/trucksDelete", method = RequestMethod.POST)
    public String deleteTrucks(ModelMap map, HttpServletRequest request) {
        log.info("I`m in /trucks post");
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            log.info("Try to remove by Id");
            log.info(Arrays.toString(ids));
            truckService.removeAllById(ids);
            log.info("removing by id was OK");
        }
        log.info("add to model trucklist");
        map.addAttribute("trucksList", truckService.truckList());
        log.info("return trucks page");
        map.addAttribute("citiesList", cityService.cityList());
        return TRUCKS_PAGE;
    }

    //stopper
    @RequestMapping(value = "/trucksAdd", method = RequestMethod.POST)
    public String addTruck(@RequestParam("regNumber") String regNumber,
                           @RequestParam("workShift") int workShift,
                           @RequestParam("loadWeight") int loadWeight,
                           @RequestParam("working") int working,
                           @RequestParam("city") String cityId,
                           ModelMap map) {
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
        map.addAttribute("trucksList", truckService.truckList());
        map.addAttribute("citiesList", cityService.cityList());
        //TODO: redirect to /trucks, because f5 add the same truck again
        return TRUCKS_PAGE;
    }

    //stopper
    @RequestMapping(value = "/trucksEdit", method = RequestMethod.POST)
    public String editTruck(@RequestParam("regNumber") String regNumber,
                            @RequestParam("workShift") int workShift,
                            @RequestParam("loadWeight") int loadWeight,
                            @RequestParam("working") int working,
                            @RequestParam("city") String city,
                            @RequestParam("idTruck") String idTruck,
                            ModelMap map) {
        log.info(idTruck + " : " + regNumber + " : " + workShift + " : " + loadWeight + " : " + working + " :cityId " + city);
        Truck updateTruck = truckService.findById(idTruck);
        updateTruck.setRegNumber(regNumber);
        updateTruck.setWorkShift(workShift);
        updateTruck.setLoadWeight(loadWeight);
        if (working == 0)
            updateTruck.setWorking(false);
        else
            updateTruck.setWorking(true);
        //TODO: make select list of city
        //updateTruck.setCity();
        truckService.update(updateTruck);
        //TODO: redirect to /trucks, because f5 add the same truck again
        map.addAttribute("trucksList", truckService.truckList());
        map.addAttribute("citiesList", cityService.cityList());
        return TRUCKS_PAGE;
    }
}
