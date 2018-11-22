package com.inc.slon.controller;

import com.inc.slon.model.Truck;
import com.inc.slon.model.form.TruckEditForm;
import com.inc.slon.model.form.TruckForm;
import com.inc.slon.service.TruckServiceFacade;
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
public class TruckController {
    private static final String TRUCKS_PAGE = "/trucks";
    @Autowired
    private TruckServiceFacade truckServiceFacade;
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
        model.addAttribute("truckForm", new TruckForm());
        model.addAttribute("truckEditForm", new TruckEditForm());
    }

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
    public ModelAndView addTruck(@Valid @ModelAttribute TruckForm truckForm) {
        log.info("(/trucksAdd,post) start");
        truckServiceFacade.addTruck(truckForm);
        log.info("(/trucksAdd,post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }

    @RequestMapping(value = "/trucksEdit", method = RequestMethod.POST)
    public ModelAndView editTruck(@Valid @ModelAttribute TruckEditForm truckEditForm) {
        log.info("(/trucksEdit,post) start");
        truckServiceFacade.editTruck(truckEditForm);
        log.info("(/trucksEdit,post) end, return ModelAndView");
        return new ModelAndView("redirect:" + TRUCKS_PAGE);
    }
}
