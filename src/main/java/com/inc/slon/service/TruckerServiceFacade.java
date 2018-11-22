package com.inc.slon.service;

import com.inc.slon.model.form.TruckerEditForm;
import com.inc.slon.model.form.TruckerForm;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

public interface TruckerServiceFacade {

    void putMap(ModelMap map);

    void addTrucker(TruckerForm truckerForm);

    void deleteTruckers(HttpServletRequest request);

    void editTrucker(TruckerEditForm truckerEditForm);
}
