package com.inc.slon.controller;

import com.inc.slon.model.form.TruckerEditForm;
import com.inc.slon.model.form.TruckerForm;
import com.inc.slon.service.TruckerServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class TruckerController {
    private static final String TRUCKERS_PAGE = "/truckers";

    @Autowired
    private TruckerServiceFacade truckerServiceFacade;

    @Autowired
    private Logger log;

    // empty strings as null
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    //add forms to the model
    @ModelAttribute
    public void bindModelAttributes(Model model){
        model.addAttribute("truckerForm", new TruckerForm());
        model.addAttribute("truckerEditForm", new TruckerEditForm());
    }

    @RequestMapping(value = "/truckers", method = RequestMethod.GET)
    public String showTruckersPage(ModelMap map) {
        log.info("(/truckers, get) start");
        truckerServiceFacade.putMap(map);
        log.info("(/truckers, get) end, return TRUCKERS_PAGE");
        return TRUCKERS_PAGE;
    }

    @RequestMapping(value = "/truckersAdd", method = RequestMethod.POST)
    public ModelAndView addTrucker(@Valid @ModelAttribute TruckerForm truckerForm){
        log.info("(/truckersAdd, post) start");
        truckerServiceFacade.addTrucker(truckerForm);
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
    public ModelAndView editTrucker(@Valid @ModelAttribute TruckerEditForm truckerEditForm) {
        log.info("(/truckersEdit, post) start");
        truckerServiceFacade.editTrucker(truckerEditForm);
        //TODO: change in citiesList to default null value with "Choose city"
        log.info("(/truckersEdit, post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKERS_PAGE);
    }
}
