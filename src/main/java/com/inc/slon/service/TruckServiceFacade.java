package com.inc.slon.service;

import com.inc.slon.model.form.TruckEditForm;
import com.inc.slon.model.form.TruckForm;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

public interface TruckServiceFacade {
    void putModelMap(ModelMap map);

    void deleteTruck(HttpServletRequest request);

    void addTruck(TruckForm truckForm);

    void editTruck(TruckEditForm truckEditForm);
}
