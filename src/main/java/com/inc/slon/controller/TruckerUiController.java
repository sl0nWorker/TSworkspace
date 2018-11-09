package com.inc.slon.controller;

import com.inc.slon.model.*;
import com.inc.slon.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.GregorianCalendar;
import java.util.List;


@Controller
public class TruckerUiController {
    private static final String TRUCKER_UI_PAGE = "truckerUi";
    @Autowired
    private Logger log;
    @Autowired
    private TruckerService truckerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TruckerHistoryShiftService truckerHistoryShiftService;
    @Autowired
    private TruckerStatusService truckerStatusService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private FreightService freightService;
    @Autowired
    private FreightStatusService freightStatusService;

    @RequestMapping(value = {"/truckerUi"}, method = RequestMethod.GET)
    public String showTruckerList(ModelMap map) {
        final String path = "(/truckerUi, get) ";
        log.info(path + "start");
        List<Trucker> truckerList = truckerService.truckerList();
        map.addAttribute("truckerList", truckerList);

        log.info(path + "end");
        return TRUCKER_UI_PAGE;
    }

    @RequestMapping(value = {"/truckerUi/trucker"}, method = {RequestMethod.POST , RequestMethod.GET})
    public ModelAndView showTruckerInfo(ModelMap map, @RequestParam(value = "truckerId") Long truckerId) {
        final String path = "(/truckerUi/trucker, post) ";
        log.info(path + "start");

        Trucker trucker = truckerService.findById(truckerId);
        map.addAttribute("trucker", trucker);
        // for selected list (where truckerId need for changing status)
        map.addAttribute("truckerId", truckerId);


        Long truckId = null;
        if (trucker.getTruck() != null) {
            truckId = trucker.getTruck().getId();
            // add cotrucker in map
            Order order = orderService.findByTruckId(truckId);
            if (order != null) {
                List<Trucker> truckerList = order.getTruckerList();
                for (Trucker t : truckerList) {
                    if (t.getId() != truckerId) {
                        map.addAttribute("cotrucker", t);
                    }
                }
                // add routeList
                List<Route> routeList = order.getRouteList();
                map.addAttribute("routeList", routeList);
            }
        }


        log.info(path + "end");
        return new ModelAndView("truckerInfo");
    }

    @RequestMapping(value = {"/truckerUi/trucker/changeShift"}, method = RequestMethod.POST)
    public ModelAndView changeShift(ModelMap map,
                                    @RequestParam(value = "truckerId") Long truckerId,
                                    @RequestParam(value = "shiftStatus") String shiftStatus) {
        final String path = "(/truckerUi/trucker/changeShift, post) ";
        log.info(path + "start");

        Trucker updateTrucker = truckerService.findById(truckerId);
        TruckerHistoryShift lastTruckerHistoryShift = truckerHistoryShiftService.lastTruckerHistoryShiftByTruckerId(truckerId);
        if (lastTruckerHistoryShift == null){
            if (shiftStatus.equals("finishShift")){
                String error ="TRY TO FINISH SHIFT WITHOUT START";
                log.error(error);
                map.addAttribute("error",error);
                return new ModelAndView("errorGeneral");
            }else{
                truckerHistoryShiftService.add(new TruckerHistoryShift( updateTrucker, "startShift", new GregorianCalendar()));
                log.info(path + "end");
                ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
                modelAndView.addObject("truckerId",truckerId);
                return modelAndView;
            }
        }
        if (shiftStatus.equals(lastTruckerHistoryShift.getShiftStatus())){
            String error = "CHANGE STATUS TO THE SAME";
            log.error(error);
            map.addAttribute("error",error);
            return new ModelAndView("errorGeneral");
        }
        if (shiftStatus.equals("finishShift") && lastTruckerHistoryShift.getShiftStatus().equals("startShift")){
            Integer workHours = updateTrucker.getTruck().getWorkShift();
            updateTrucker.setWorkHours(workHours + updateTrucker.getWorkHours());
            truckerService.update(updateTrucker);
            truckerHistoryShiftService.add(new TruckerHistoryShift( updateTrucker, "finishShift", new GregorianCalendar()));
            log.info("ADD workHours: " + workHours + " to trucker: " + updateTrucker);
        } else{
            log.info("new shiftStatus: " + shiftStatus + ", old shiftStatus: " + lastTruckerHistoryShift.getShiftStatus());
            truckerHistoryShiftService.add(new TruckerHistoryShift( updateTrucker, "startShift", new GregorianCalendar()));
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }

    @RequestMapping(value = {"/truckerUi/trucker/changeStatus"}, method = {RequestMethod.POST , RequestMethod.GET})
    public ModelAndView changeTruckerStatus(ModelMap map,
                                            @RequestParam(value = "truckerId") Long truckerId,
                                            @RequestParam(value = "changeStatus") String changeStatus) {
        final String path = "(/truckerUi/trucker/changeStatus, post) ";
        log.info(path + "start");

        Trucker updateTrucker = truckerService.findById(truckerId);
        TruckerStatus truckerStatus = truckerStatusService.findByName(changeStatus);
        if (truckerStatus != null){
            updateTrucker.setStatus(truckerStatus);
        }else{
            log.error(path + "truckerStatus == null");
        }
        truckerService.update(updateTrucker);

        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }
    @RequestMapping(value = {"/truckerUi/trucker/changeFreightStatus"}, method = RequestMethod.POST )
    public ModelAndView changeFreightStatus(ModelMap map,
                                            @RequestParam(value ="routeId") Long routeId,
                                            @RequestParam(value = "freightId") Long freightId,
                                            @RequestParam(value = "unloading") Boolean unloading,
                                            @RequestParam(value = "truckerId") Long truckerId) {
        final String path = "(/truckerUi/trucker/changeFreightStatus, post) ";
        String error;
        log.info(path + "start");
        Freight updateFreight = freightService.findById(freightId);
        Route updateRoute = routeService.findById(routeId);
        if (unloading == false ){
            if (updateFreight.getFreightStatus().getStatus().equals("Prepared")){
                updateFreight.setFreightStatus(freightStatusService.findByStatusName("Shipped"));
                freightService.update(updateFreight);
                updateRoute.setComplete(true);
                routeService.update(updateRoute);
            } else {
                error = "unloading == false, but status != prepared";
                log.error(path + "unloading == false, but status != prepared");
                map.addAttribute("error",error);
                return new ModelAndView("errorGeneral");
            }
        } else{
            if (updateFreight.getFreightStatus().getStatus().equals("Shipped")){
                updateFreight.setFreightStatus(freightStatusService.findByStatusName("Delivered"));
                freightService.update(updateFreight);
                updateRoute.setComplete(true);
                routeService.update(updateRoute);
            } else{
                error = "unloading == true, but status != shipped";
                log.error(path + "unloading == true, but status != shipped");
                map.addAttribute("error",error);
                return new ModelAndView("errorGeneral");
            }
        }


        ModelAndView modelAndView = new ModelAndView("redirect:/truckerUi/trucker");
        modelAndView.addObject("truckerId",truckerId);
        log.info(path + "end");
        return modelAndView;
    }
}
