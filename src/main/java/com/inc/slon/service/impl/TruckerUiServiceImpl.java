package com.inc.slon.service.impl;

import com.inc.slon.model.*;
import com.inc.slon.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TruckerUiServiceImpl implements TruckerUiService {
    @Autowired
    TruckerService truckerService;

    @Autowired
    OrderService orderService;

    @Autowired
    TruckerHistoryShiftService truckerHistoryShiftService;

    @Autowired
    TruckerStatusService truckerStatusService;

    @Autowired
    RouteService routeService;

    @Autowired
    FreightService freightService;

    @Autowired
    FreightStatusService freightStatusService;

    @Autowired
    private ArchiveOrderService archiveOrderService;

    @Autowired
    Logger log;


    @Transactional
    @Override
    public void putModelMap(ModelMap map) {
        List<Trucker> truckerList = truckerService.truckerList();
        map.addAttribute("truckerList", truckerList);
    }

    @Transactional
    @Override
    public void showTruckerInfo(ModelMap map, Long truckerId) {
        Trucker trucker = truckerService.findById(truckerId);
        map.addAttribute("trucker", trucker);
        // for selected list (where truckerId need for changing status)
        map.addAttribute("truckerId", truckerId);


        Long truckId;
        if (trucker.getTruck() != null && trucker.getTruck().getOrder() != null) {
            truckId = trucker.getTruck().getId();
            // add cotrucker in mapx1x1
            Order order = orderService.findByTruckId(truckId);
            if (order != null) {
                List<Trucker> truckerList = order.getTruckerList();
                for (Trucker t : truckerList) {
                    if (!t.getId().equals(truckerId)) {
                        map.addAttribute("cotrucker", t);
                    }
                }
                // add routeList
                List<Route> routeList = order.getRouteList();
                //check for ordrerReady
                boolean complete = true;
                for (Route route : routeList) {
                    if (!route.getComplete()) {
                        complete = false;
                        break;
                    }
                }
                if (complete) {
                    map.addAttribute("orderReady", true);
                }
                map.addAttribute("routeList", routeList);
            }
        }

    }

    @Transactional
    @Override
    public void changeTruckerShift(Long truckerId, String shiftStatus) {
        final String path = "(/truckerUi/trucker/changeShift, post) ";
        Trucker updateTrucker = truckerService.findById(truckerId);
        TruckerHistoryShift lastTruckerHistoryShift = truckerHistoryShiftService.lastTruckerHistoryShiftByTruckerId(truckerId);
        if (lastTruckerHistoryShift == null) {
            if (shiftStatus.equals("finishShift")) {
                String error = "TRY TO FINISH SHIFT WITHOUT START";
                log.error(error);
                throw new RuntimeException(error);
            } else {
                truckerHistoryShiftService.add(new TruckerHistoryShift(updateTrucker, "startShift", new GregorianCalendar()));
                log.info(path + "end");
            }
        } else {
            if (shiftStatus.equals(lastTruckerHistoryShift.getShiftStatus())) {
                String error = "CHANGE STATUS TO THE SAME";
                log.error(error);
                throw new RuntimeException(error);
            }
            if (shiftStatus.equals("finishShift") && lastTruckerHistoryShift.getShiftStatus().equals("startShift")) {
                Integer workHours = updateTrucker.getTruck().getWorkShift();
                updateTrucker.setWorkHours(workHours + updateTrucker.getWorkHours());
                truckerService.update(updateTrucker);
                truckerHistoryShiftService.add(new TruckerHistoryShift(updateTrucker, "finishShift", new GregorianCalendar()));
                log.info("ADD workHours: " + workHours + " to trucker: " + updateTrucker);
            } else {
                log.info("new shiftStatus: " + shiftStatus + ", old shiftStatus: " + lastTruckerHistoryShift.getShiftStatus());
                truckerHistoryShiftService.add(new TruckerHistoryShift(updateTrucker, "startShift", new GregorianCalendar()));
            }
        }
    }

    @Transactional
    @Override
    public void changeTruckerStatus(Long truckerId, String changeStatus) {

        Trucker updateTrucker = truckerService.findById(truckerId);
        TruckerStatus truckerStatus = truckerStatusService.findByName(changeStatus);
        if (truckerStatus != null) {
            updateTrucker.setStatus(truckerStatus);
        } else {
            log.error("(/truckerUi/trucker/changeStatus, post) truckerStatus == null");
        }
        truckerService.update(updateTrucker);
    }

    private boolean checkPreviousRoutesAreComplited(List<Route> routeList, Long routeId) {
        if (routeList != null && routeList.size() != 0) {
            for (Route route : routeList) {
                if (!route.getComplete()) {
                    if(route.getId().equals(routeId)){
                       break;
                    }
                    return false;
                }
            }
            return true;
        } else {
            throw new RuntimeException("changeFreightStatus, but routeList in order null or size = 0");
        }
    }

    @Transactional
    @Override
    public void changeFreightStatusInTruckerOrder(Long routeId, Long freightId, Boolean unloading, Long truckerId) {
        final String path = "(/truckerUi/trucker/completeOrder, post) ";
        String error;
        log.info(path + "start");
        // get the routeList for checking that all previous routes are completed
        Trucker trucker = truckerService.findById(truckerId);
        List<Route> routeList = trucker.getTruck().getOrder().getRouteList();
        boolean isPreviousRoutesAreCompleted = checkPreviousRoutesAreComplited(routeList,routeId);
        if (!isPreviousRoutesAreCompleted){
            throw new RuntimeException("previous routes are not completed");
        }

        Freight updateFreight = freightService.findById(freightId);
        Route updateRoute = routeService.findById(routeId);
        if (!unloading) {
            if (updateFreight.getFreightStatus().getStatus().equals("Prepared")) {
                updateFreight.setFreightStatus(freightStatusService.findByStatusName("Shipped"));
                freightService.update(updateFreight);
                updateRoute.setComplete(true);
                routeService.update(updateRoute);
            } else {
                error = "unloading == false, but status != prepared";
                log.error(path + "unloading == false, but status != prepared");
                throw new RuntimeException(error);

            }
        } else {
            if (updateFreight.getFreightStatus().getStatus().equals("Shipped")) {
                updateFreight.setFreightStatus(freightStatusService.findByStatusName("Delivered"));
                freightService.update(updateFreight);
                updateRoute.setComplete(true);
                routeService.update(updateRoute);
            } else {
                error = "unloading == true, but status != shipped";
                log.error(path + "unloading == true, but status != shipped");
                throw new RuntimeException(error);
            }
        }
    }

    @Transactional
    @Override
    public void completeTruckerOrder(Long truckerId, Long orderId, HttpSession httpSession) {
        Order order = orderService.findById(orderId);
        List<Trucker> truckerList = order.getTruckerList();
        for (Trucker trucker : truckerList) {
            trucker.setTruck(null);
            trucker.setStatus(truckerStatusService.findByName("FREE"));
            truckerService.update(trucker);
        }
        //remove in session orderList
        boolean remove = false;
        int idx = 0;
        //TODO:unchecked cast
        List<Order> orderList = (List<Order>) httpSession.getAttribute("orderList");
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId().equals(orderId)) {
                idx = i;
                remove = true;
                break;
            }
        }
        orderList.remove(idx);

        //add order to archive
        ArchiveOrder archiveOrder = new ArchiveOrder(order);
        archiveOrderService.add(archiveOrder);

        orderService.removeById(orderId);

        log.info("(/truckerUi/trucker/completeOrder, post) remove in session orderlist " + remove);
        httpSession.setAttribute("orderList", orderList);
    }
}
