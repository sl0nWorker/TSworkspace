package com.inc.slon.service.impl;

import com.inc.slon.model.City;
import com.inc.slon.model.Trucker;
import com.inc.slon.model.TruckerStatus;
import com.inc.slon.service.CityService;
import com.inc.slon.service.TruckerService;
import com.inc.slon.service.TruckerServiceFacade;
import com.inc.slon.service.TruckerStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class TruckerServiceFacadeImpl implements TruckerServiceFacade {
    @Autowired
    private TruckerService truckerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TruckerStatusService truckerStatusService;
    @Autowired
    private Logger log;

    @Transactional
    @Override
    public void putMap(ModelMap map) {
        map.addAttribute("truckersList", truckerService.truckerList());
        map.addAttribute("citiesList", cityService.cityList());
        map.addAttribute("truckerStatusList", truckerStatusService.truckerStatusList());
    }

    @Transactional
    @Override
    public void addTrucker(String firstName, String lastName, int personalNumber, String cityId) {
        Trucker truckerAdd = new Trucker();
        log.info("try to set firstName");
        truckerAdd.setFirstName(firstName);
        truckerAdd.setLastName(lastName);
        truckerAdd.setPersonalNumber(personalNumber);
        truckerAdd.setWorkHours(0);
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
    }

    @Transactional
    @Override
    public void deleteTruckers(HttpServletRequest request) {
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
    }

    @Transactional
    @Override
    public void editTrucker(String firstName, String lastName, Integer personalNumber, Integer workHours, String statusId, String cityId, Long truckerId) {

        //TODO: checking if trucker is FREE, else ERROR MESSAGE: "you can`t edit this trucker"

        log.info(truckerId + " : " + firstName + " : " + lastName + " : " + personalNumber + " : " + workHours + " : " + statusId + " :cityId " + cityId);

        Trucker updateTrucker = truckerService.findById(truckerId);

        if (firstName != null && !firstName.equals("")) {
            updateTrucker.setFirstName(firstName);
        } else {
            log.info("(/truckerEdit, post) firstName = null or empty");
        }

        if (lastName != null && !lastName.equals("")) {
            updateTrucker.setLastName(lastName);
        } else {
            log.info("(/truckerEdit, post) lastName = null or empty");
        }

        if (personalNumber != null) {
            updateTrucker.setPersonalNumber(personalNumber);
        } else {
            log.info("(/truckerEdit, post) personalNumber = null");
        }

        if (workHours != null) {
            updateTrucker.setWorkHours(workHours);
        } else {
            log.info("(/truckerEdit, post) workHours = null");
        }

        if (statusId != null && !statusId.equals("")) {
            updateTrucker.setStatus(truckerStatusService.findById(statusId));
        } else {
            log.info("(/truckerEdit, post) statusId = null or empty");
        }

        if (cityId != null && !cityId.equals("")) {
            log.info("changing cityName in updateTrucker to: " + cityService.findById(cityId).getCityName());
            updateTrucker.setCity(cityService.findById(cityId));
            log.info("updating city was ok");
        } else {
            log.info("(/truckersEdit, post) cityId = null or empty");
        }

        log.info("(/truckersEdit, post) try to update Trucker");
        truckerService.update(updateTrucker);
    }
}
