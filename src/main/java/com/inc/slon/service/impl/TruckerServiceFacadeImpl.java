package com.inc.slon.service.impl;

import com.inc.slon.model.City;
import com.inc.slon.model.Truck;
import com.inc.slon.model.Trucker;
import com.inc.slon.model.TruckerStatus;
import com.inc.slon.model.form.TruckerEditForm;
import com.inc.slon.model.form.TruckerForm;
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
    public void addTrucker(TruckerForm truckerForm) {
        Trucker truckerAdd = new Trucker(truckerForm);
        TruckerStatus truckerStatus = truckerStatusService.findById("1");
        if (truckerStatus.getStatus().equals("FREE")) {
            truckerAdd.setStatus(truckerStatus);
        } else {
            log.error("addTrucker, TRUCKER_STATUS with id:1  must be FREE");
            throw new RuntimeException("addTrucker,TRUCKER_STATUS with id:1  must be FREE");
        }
        City cityAdd = cityService.findById(truckerForm.getCityId());
        truckerAdd.setCity(cityAdd);
        truckerService.add(truckerAdd);
    }

    @Transactional
    @Override
    public void deleteTruckers(HttpServletRequest request) {
        log.info("I`m in /truckersDelete post");
        String[] ids = request.getParameterValues("id");
        if (ids != null && ids.length > 0) {
            for (String id: ids){
                Trucker trucker = truckerService.findById(Long.valueOf(id));
                if (trucker.getTruck() != null){
                    throw new RuntimeException("Can not delete the trucker on an order!");
                }
            }
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
    public void editTrucker(TruckerEditForm truckerEditForm) {

        Trucker updateTrucker = truckerService.findById(Long.valueOf(truckerEditForm.getTruckerId()));
        // checking if the trucker has not an order
        if(updateTrucker.getTruck() != null) {
            throw new RuntimeException("Can not edit the trucker on an order!");
        }

        if (truckerEditForm.getFirstName() != null && !truckerEditForm.getFirstName().equals("")) {
            updateTrucker.setFirstName(truckerEditForm.getFirstName());
        }

        if (truckerEditForm.getLastName()!= null && !truckerEditForm.equals("")) {
            updateTrucker.setLastName(truckerEditForm.getLastName());
        }

        if (truckerEditForm.getPersonalNumber()!= null) {
            updateTrucker.setPersonalNumber(Integer.valueOf(truckerEditForm.getPersonalNumber()));
        }

        if (truckerEditForm.getWorkHours()!= null) {
            updateTrucker.setWorkHours(Integer.valueOf(truckerEditForm.getWorkHours()));
        }

        String cityId = truckerEditForm.getCityId();
        if (cityId != null) {
            log.info("changing cityName in updateTrucker to: " + cityService.findById(cityId).getCityName());
            updateTrucker.setCity(cityService.findById(cityId));
            log.info("updating city was ok");
        }


        truckerService.update(updateTrucker);
        log.info("editTrucker updating Trucker");
    }
}
