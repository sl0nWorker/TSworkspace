package com.inc.slon.controller;

import com.inc.slon.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;



@Controller
public class TruckerUiController {
    private static final String TRUCKER_UI_PAGE = "truckerUi";
    @Autowired
    private Logger log;

    @Autowired
    private TruckerUiService truckerUiService;

    @RequestMapping(value = {"/truckerUi"}, method = RequestMethod.GET)
    public String showTruckerList(ModelMap map) {
        final String path = "(/truckerUi, get) ";
        log.info(path + "start");
        truckerUiService.putModelMap(map);
        log.info(path + "end");
        return TRUCKER_UI_PAGE;
    }

    @RequestMapping(value = {"/truckerUi/trucker"}, method = {RequestMethod.POST , RequestMethod.GET})
    public ModelAndView showTruckerInfo(ModelMap map, @RequestParam(value = "truckerId") Long truckerId) {
        final String path = "(/truckerUi/trucker, post) ";
        log.info(path + "start");
        truckerUiService.showTruckerInfo(map,truckerId);
        log.info(path + "end");
        return new ModelAndView("truckerInfo");
    }

    @RequestMapping(value = {"/truckerUi/trucker/changeShift"}, method = RequestMethod.POST)
    public ModelAndView changeShift(@RequestParam(value = "truckerId") Long truckerId,
                                    @RequestParam(value = "shiftStatus") String shiftStatus) {
        final String path = "(/truckerUi/trucker/changeShift, post) ";
        log.info(path + "start");
        truckerUiService.changeTruckerShift(truckerId, shiftStatus);
        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }

    @RequestMapping(value = {"/truckerUi/trucker/changeStatus"}, method = {RequestMethod.POST , RequestMethod.GET})
    public ModelAndView changeTruckerStatus(@RequestParam(value = "truckerId") Long truckerId,
                                            @RequestParam(value = "changeStatus") String changeStatus) {
        final String path = "(/truckerUi/trucker/changeStatus, post) ";
        log.info(path + "start");
        truckerUiService.changeTruckerStatus(truckerId,changeStatus);
        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }
    @RequestMapping(value = {"/truckerUi/trucker/changeFreightStatus"}, method = RequestMethod.POST )
    public ModelAndView changeFreightStatus(@RequestParam(value ="routeId") Long routeId,
                                            @RequestParam(value = "freightId") Long freightId,
                                            @RequestParam(value = "unloading") Boolean unloading,
                                            @RequestParam(value = "truckerId") Long truckerId) {
        final String path = "(/truckerUi/trucker/changeFreightStatus, post) ";
        truckerUiService.changeFreightStatusInTruckerOrder(routeId, freightId, unloading, truckerId);
        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }

    @RequestMapping(value = {"/truckerUi/trucker/completeOrder"}, method = RequestMethod.POST)
    public ModelAndView completeOrder(@RequestParam(value = "truckerId") Long truckerId,
                                      @RequestParam(value = "orderId") Long orderId, HttpSession httpSession) {
        final String path = "(/truckerUi/trucker/completeOrder, post) ";
        log.info(path + "start");
        truckerUiService.completeTruckerOrder(truckerId,orderId,httpSession);
        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }
}
