package com.inc.slon.service.impl;

import com.inc.slon.model.City;
import com.inc.slon.model.Truck;
import com.inc.slon.model.form.TruckEditForm;
import com.inc.slon.model.form.TruckForm;
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
            for (String id: ids){
                Truck truck = truckService.findById(id);
                if (truck.getOrder() != null){
                    throw new RuntimeException("Can not delete the truck on an order!");
                }
            }
            log.info("Try to remove by Id");
            log.info(Arrays.toString(ids));
            truckService.removeAllById(ids);
            log.info("removing by id was OK");
        } else{
            log.error("deleteTruck, [] ids = null or length = 0");
        }
    }

    @Transactional
    @Override
    public void addTruck(TruckForm truckForm) {
        City cityAdd = cityService.findById(truckForm.getCityId());
        Truck truckAdd = new Truck(truckForm, cityAdd);
        log.info("Try add truck with city");
        truckService.add(truckAdd);
        log.info("add truck with city was OK");

    }

    @Transactional
    @Override
    public void editTruck(TruckEditForm truckEditForm) {
        Truck updateTruck;
        String idTruck = truckEditForm.getIdTruck();
        if (idTruck != null && !idTruck.equals("")) {
            updateTruck = truckService.findById(idTruck);
            if (updateTruck.getOrder() != null){
                throw new RuntimeException("Can not edit the truck on an order!");
            }
        } else {
            log.error("(/trucksEdit,post) idTruck = null or empty, updating truck is impossible");
            throw new RuntimeException("editTruck, idTruck = null or empty, updating truck is impossible");
        }

        if(updateTruck != null) {
            if (truckEditForm.getRegNumber() != null && !truckEditForm.getRegNumber().equals("")) {
                updateTruck.setRegNumber(truckEditForm.getRegNumber());
            }
            if (truckEditForm.getWorkShift()!= null) {
                updateTruck.setWorkShift(Integer.valueOf(truckEditForm.getWorkShift()));
            }
            if (truckEditForm.getLoadWeight()!= null) {
                updateTruck.setLoadWeight(Integer.valueOf(truckEditForm.getLoadWeight()));
            }

            if (truckEditForm.getWorking() != null) {
                updateTruck.setWorking(truckEditForm.getWorking());
            }

            String city = truckEditForm.getCityId();
            if (city != null) {
                log.info("changin cityName in updateTruck to: " + cityService.findById(city).getCityName());
                updateTruck.setCity(cityService.findById(city));
                log.info("updating city was ok");
            }
            truckService.update(updateTruck);
        } else {
            throw new RuntimeException("editTruck, updateTruck not found by id ");
        }
    }
}
