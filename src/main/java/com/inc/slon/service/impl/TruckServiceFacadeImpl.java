package com.inc.slon.service.impl;

import com.inc.slon.model.City;
import com.inc.slon.model.Truck;
import com.inc.slon.service.CityService;
import com.inc.slon.service.TruckService;
import com.inc.slon.service.TruckServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class TruckServiceFacadeImpl implements TruckServiceFacade {
    @Autowired
    TruckService truckService;
    @Autowired
    CityService cityService;
    @Autowired
    private Logger log;

    @Transactional
    @Override
    public void putModelMap(ModelMap map) {
        map.addAttribute("trucksList", truckService.truckList());
        map.addAttribute("citiesList", cityService.cityList());
    }

    @Transactional
    @Override
    public void deleteTruck(HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            log.info("Try to remove by Id");
            log.info(Arrays.toString(ids));
            truckService.removeAllById(ids);
            log.info("removing by id was OK");
        }
    }

    @Transactional
    @Override
    public void addTruck(String regNumber, Integer workShift, Integer loadWeight, Boolean working, String cityId) {

        City cityAdd = cityService.findById(cityId);
        Truck truckAdd = new Truck(regNumber, workShift, loadWeight, working, cityAdd);
        log.info("Try add truck with city");
        truckService.add(truckAdd);
        log.info("add truck with city was OK");

    }

    @Transactional
    @Override
    public void editTruck(String regNumber, Integer workShift, Integer loadWeight, Boolean working, String city, String idTruck) {
        Truck updateTruck = null;
        if (idTruck != null && !idTruck.equals("")) {
            updateTruck = truckService.findById(idTruck);
        } else {
            log.error("(/trucksEdit,post) idTruck = null or empty, updating truck is impossible");
        }

        if(updateTruck != null) {
            if (regNumber != null && !regNumber.equals("")) {
                updateTruck.setRegNumber(regNumber);
            }
            if (workShift != null) {
                updateTruck.setWorkShift(workShift);
            }
            if (loadWeight != null) {
                updateTruck.setLoadWeight(loadWeight);
            }

            if (working != null) {
                updateTruck.setWorking(working);
            }

            if (city != null && !city.equals("")) {
                log.info("changin cityName in updateTruck to: " + cityService.findById(city).getCityName());
                updateTruck.setCity(cityService.findById(city));
                log.info("updating city was ok");
            }
            truckService.update(updateTruck);
        }
    }
}
