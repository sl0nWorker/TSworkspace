package com.inc.slon.service.impl;

import com.inc.slon.model.*;
import com.inc.slon.model.form.RouteForm;
import com.inc.slon.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RouteServiceFacadeImpl implements RouteServiceFacade {
    @Autowired
    Logger log;

    @Autowired
    CityService cityService;

    @Autowired
    FreightStatusService freightStatusService;

    @Autowired
    TruckService truckService;

    @Autowired
    TruckerService truckerService;

    @Autowired
    TruckerStatusService truckerStatusService;

    @Autowired
    CountryMapService countryMapService;

    @Autowired
    OrderService orderService;

    @Autowired
    FreightService freightService;

    @Autowired
    RouteService routeService;

    final static int loadUnloadHours = 2;

    @Transactional
    @Override
    public void putModelMapAndHttpSession(ModelMap map, HttpSession httpSession) {
        if (httpSession.getAttribute("routeList") == null) {
            List<Route> routeList = new ArrayList<>();
            httpSession.setAttribute("routeList", routeList);
            map.addAttribute("routeList", routeList);
            map.addAttribute("citiesList", cityService.cityList());
        } else {
            // TODO: add type checks
            List<Route> routeList = (List<Route>) httpSession.getAttribute("routeList");
            map.addAttribute("routeList", routeList);
            log.info("routeList!=null: size of list = " + routeList.size());
            map.addAttribute("citiesList", cityService.cityList());
        }
    }

    private boolean isLoadingBeforeUnloadingAndUniqueFreightNumber(RouteForm routeForm, List<Route> routeList) {
        Integer checkingFreightNumber = Integer.valueOf(routeForm.getFreightNumber());

        if (routeForm.getLoading().equals("Loading")){
            for (Route route: routeList) {
                if(route.getFreight().getFreightNumber().equals(checkingFreightNumber)){
                   throw new RuntimeException("Freight with freightNumber: " + routeForm.getFreightNumber() + " must has only a one loading route point");
                }
            }
            // freight with the same number not found, can add loading
            return true;
        } else{
            boolean hasloadingWithSameFreighNumber = false;
            for (Route route: routeList){
                if (route.getFreight().getFreightNumber().equals(checkingFreightNumber)){
                    if(route.getUnloading()) {
                        throw new RuntimeException("Freight with freightNumber: " + routeForm.getFreightNumber() + " must has only a one unloading route point");
                    }
                    //check if the name and weight filds are equals for the same freightNumber
                    if (!route.getFreight().getName().equals(routeForm.getFreightName()) || !route.getFreight().getWeight().equals(Integer.valueOf(routeForm.getWeight()))){
                        log.info("here:!!!!!!!!!!!!!!  "+!route.getFreight().getName().equals(routeForm.getFreightName()) +" " + !route.getFreight().getWeight().equals(routeForm.getWeight()));
                        throw new RuntimeException("fields freight name and freight weight must be same in route points with equals freightNumber");
                    }
                    hasloadingWithSameFreighNumber = true;
                }
            }
            if(hasloadingWithSameFreighNumber) {
                return true;
            }
            // freight with the same number not found, can`t add unloading
            return false;
        }
    }

    @Transactional
    @Override
    public void addRoute(ModelMap map, HttpSession httpSession, RouteForm routeForm) {
        //TODO: add checking that loading is ahead of unloading. If unloading , check existing way from A to B
        // TODO: add type checks

        List<Route> routeList = (List<Route>) httpSession.getAttribute("routeList");
        //check loading before unloading and unique freight number
        if (!isLoadingBeforeUnloadingAndUniqueFreightNumber(routeForm,routeList)){
            throw new RuntimeException("freight with the same number not found, can`t add unloading");
        }

        log.info("/createRouteList,post getOrder");

        if (routeList != null && routeList.size() != 0) {
            log.info("/createRouteList,post getRouteList");
            log.info("route: " + routeList.get(0));
        }
        Route route = new Route();
        //TODO: add checking cityService.findById
        log.info("Find cityID");
        route.setCity(cityService.findById(routeForm.getCityId()));
        //TODO: persist route after freight
        Freight freight = new Freight();
        freight.setFreightNumber(Integer.valueOf(routeForm.getFreightNumber()));
        freight.setName(routeForm.getFreightName());
        freight.setWeight(Integer.valueOf(routeForm.getWeight()));
        log.info("Find freightStatus");
        log.info("List status: " + freightStatusService.statusList());
        log.info("FINDBYSTATUSNAME: " + freightStatusService.findByStatusName("Prepared"));
        freight.setFreightStatus(freightStatusService.findByStatusName("Prepared"));
        route.setFreight(freight);

        if (routeForm.getLoading().equals("Loading")) {
            route.setUnloading(false);
        } else {
            route.setUnloading(true);
        }
        routeList.add(route);
        log.info("set route: " + routeList.size());
        httpSession.setAttribute("routeList", routeList);
        map.addAttribute("routeList", routeList);
    }

    @Transactional
    @Override
    public void deleteRoutes(ModelMap map, HttpSession httpSession, Integer[] ids) {
        // TODO: add type checks
        List<Route> routeList = (List<Route>) httpSession.getAttribute("routeList");
        log.info("RouteList before remove");
        for (Route route : routeList) {
            log.info(route);
        }
        // after removing an element in a list, indexing will be shifted by 1
        int offset = 1;
        for (int i = 0; i < ids.length; i++) {
            log.info("remove ids: " + (ids[i] - offset));
            routeList.remove(ids[i] - offset++);
        }
        log.info("RouteList after remove");
        for (Route route : routeList) {
            log.info(route);
        }
        httpSession.setAttribute("routeList", routeList);
        map.addAttribute("routeList", routeList);
    }

    @Transactional
    @Override
    public void saveRouteList(ModelMap map, HttpSession httpSession) {
        //TODO: add checks for changing status (someone else assigned truck or truckers)
        final String path = "(/createRouteList/saveRouteList, post) ";
        //type checks
        List<Route> savedRouteList = (List<Route>) httpSession.getAttribute("routeList");
        httpSession.setAttribute("assignedTruck", null);
        httpSession.setAttribute("assignedTruckId", null);
        httpSession.setAttribute("checkedTruckerList", null);
        httpSession.setAttribute("savedTruckerList", null);

        //checks for frieght
        Integer freightNumber;
        Integer comparedFreightNumber;
        int countPairNumbers = 0;
        String error;
        for (int i = 0; i < savedRouteList.size(); i++) {
            freightNumber = savedRouteList.get(i).getFreight().getFreightNumber();
            for (int j = 0; j < savedRouteList.size(); j++) {
                //check for existing only two equals freightNumbers (load/unload)
                comparedFreightNumber = savedRouteList.get(j).getFreight().getFreightNumber();
                if (comparedFreightNumber == freightNumber && i != j) {
                    countPairNumbers++;
                }
            }
            if (countPairNumbers != 1) {
                log.error("(/createRouteList/saveRouteList , post) freightPairNumber <2 or >2");
                error = "not all pairs completed";
                throw new RuntimeException(error);
            }
            countPairNumbers = 0;
        }
        //end checking for freight

        httpSession.setAttribute("savedRouteList", savedRouteList);


        List<Truck> truckList = truckService.truckList();
        List<Truck> freeTruckList = new ArrayList<>();
        // Free trucks, orderId = null.
        // TODO: add checks
        // working = true
        // weight is ok, with loading/unloading
        // not in another order
        int sum = 0;
        int maxWeight = 0;

        //loadWeight routes
        for (Route route : savedRouteList) {
            log.info(path + "route: " + route);
            if (!route.getUnloading()) {
                sum = sum + route.getFreight().getWeight();
                log.info("(/createRouteList/saveRouteList , post) sum step on loading: " + sum);
                if (sum > maxWeight) {
                    maxWeight = sum;
                    log.info(path + "new maxWeight: " + maxWeight);
                }
            } else {
                sum = sum - route.getFreight().getWeight();
                log.info("(/createRouteList/saveRouteList , post) sum step on unloading: " + sum);
            }
        }
        if (sum != 0) {
            log.error(path + "sum != 0");
        }
        // check truck loadWeight > maxWeight
        for (Truck truck : truckList) {
            if (truck.getOrder() == null && truck.getWorking() && truck.getLoadWeight() * 1000 >= maxWeight) {
                freeTruckList.add(truck);
                log.info(path + "(true) truck loadWeight: " + truck.getLoadWeight() + " maxWeight: " + maxWeight);
            } else {
                log.info(path + "(false) Order: " + truck.getOrder() + " Working: " + truck.getWorking() + " loadWeight: " + truck.getLoadWeight() + " maxWeight: " + maxWeight);
            }
        }
        //change to map
        httpSession.setAttribute("freeTruckList", freeTruckList);
    }

    @Transactional
    @Override
    public void assignTruck(HttpSession httpSession, String truckId) {
        final String path = "(/createRouteList/saveRouteList/assignTruck, post)";
        //average time for loading/unloading a freight
        List<Route> savedRouteList = (List<Route>) httpSession.getAttribute("savedRouteList");
        // TODO: add checks
        if(savedRouteList == null || savedRouteList.size() == 0){
            throw new RuntimeException("assignTruck, savedRouteList: null or size = 0");
        }
        //assign the truck
        if (httpSession.getAttribute("assignedTruckId") == null) {
            if (truckId != null) {
                httpSession.setAttribute("assignedTruckId", truckId);
                Truck assignedTruck = truckService.findById(truckId);
                httpSession.setAttribute("assignedTruck", assignedTruck);

                //send appropriate truckerList
                List<Trucker> checkedTruckerList = new ArrayList<>();
                List<Trucker> truckerList = truckerService.truckerList();
                if (truckerList == null) {
                    //TODO: Repair that. Session will be corrupted
                    log.info(path + " truckerList is empty, after assign truck");
                } else {
                    for (Trucker trucker : truckerList) {
                        if (isTruckerChecks(trucker,assignedTruck,savedRouteList)) {
                            checkedTruckerList.add(trucker);
                        } else{
                            log.info(path + "(false) " + trucker);
                        }
                    }
                }
                httpSession.setAttribute("checkedTruckerList", checkedTruckerList);
                //set savedTruckcerList  (for savedTruckerList.size = 0, in jsp check)
                List<Trucker> savedTruckerList = new ArrayList<>();
                httpSession.setAttribute("savedTruckerList", savedTruckerList);
            }
        }
    }

    @Transactional
    @Override
    public void assignTrucker(HttpSession httpSession, Long truckerId) {
        // TODO: add checks
        if (truckerId == null) {
            log.error("(/createRouteList/saveRouteList/assignTrucker, post) truckId = null");
            // throw exception
        }

        // add tpye checks
        List<Trucker> savedTruckerList = (List<Trucker>) httpSession.getAttribute("savedTruckerList");
        Trucker truckerAdd = truckerService.findById(truckerId);
        savedTruckerList.add(truckerAdd);
        httpSession.setAttribute("savedTruckerList", savedTruckerList);

        //send appropriate truckerList

        Truck assignedTruck = (Truck) httpSession.getAttribute("assignedTruck");
        List<Route> savedRouteList = (List<Route>) httpSession.getAttribute("savedRouteList");
        //add type checks
        if (savedTruckerList.size() == 1) {
            List<Trucker> truckerList = truckerService.truckerList();
            List<Trucker> checkedTruckerList = new ArrayList<>();
            // check time limit = 176 hours
            // trucker is free
            // same city as the assignedTruck
            // DELETE trucker with truckerId in checkedTruckerList
            for (Trucker trucker : truckerList) {
                if (isTruckerChecks(trucker,assignedTruck, savedRouteList) && !trucker.getId().equals(truckerAdd.getId())) {
                    checkedTruckerList.add(trucker);
                } else {
                    log.info( "(/createRouteList/saveRouteList/assignTrucker, post) (false) " + trucker);
                }
            }
            log.info("chekedTruckerList:" + checkedTruckerList + " size: " + checkedTruckerList.size());
            httpSession.setAttribute("checkedTruckerList", checkedTruckerList);
        }
    }

    private boolean isTruckerChecks(Trucker trucker, Truck assignedTruck, List<Route> savedRouteList){
        // check time limit = 176 hours
        // trucker is free
        // same city as the assignedTruck
        //first check for not in another order
        if (trucker.getTruck() == null && trucker.getCity().getId() == assignedTruck.getCity().getId() && trucker.getStatus().getStatus().equals("FREE")
                && (176 - trucker.getWorkHours()) > countryMapService.timeForRouteList(savedRouteList) + loadUnloadHours){
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public void createOrder(HttpSession httpSession) {
        final String path = "(/createRouteList/saveRouteList/assignOrder , post) ";
        Order order = new Order();
        order.setReady(false);
        Truck truck = (Truck) httpSession.getAttribute("assignedTruck");
        order.setTruck(truck);

        List<Route> routeList = (List<Route>) httpSession.getAttribute("savedRouteList");
        order.setRouteList(routeList);
        List<Trucker> truckerList = (List<Trucker>) httpSession.getAttribute("savedTruckerList");
        order.setTruckerList(truckerList);

        // TODO: clean httpSession
        httpSession.setAttribute("routeList", null);
        httpSession.setAttribute("savedRouteList", null);
        httpSession.setAttribute("savedTruckerList", null);
        httpSession.setAttribute("assignedTruck", null);
        httpSession.setAttribute("assignedTruckId", null);
        httpSession.setAttribute("checkedTruckerList", null);



        // assign truck to truckers and change truckers status (NOT FREE)
        for (Trucker trucker:truckerList) {
            trucker.setTruck(truck);
            trucker.setStatus(truckerStatusService.findByName("WORK"));
            truckerService.update(trucker);
        }

        // TODO: add pre persisting checks (status is still the same)
        //for status truck, for status trucker.
        Freight freightToPersist;
        Set<Integer> freightNumberSet = new HashSet<>();
        for (Route route : routeList) {
            freightToPersist = route.getFreight();
            log.info(path + route.getFreight());
            if (!freightNumberSet.contains(freightToPersist.getFreightNumber())){
                log.info(path + "not contains, adding " + freightToPersist);
                freightNumberSet.add(freightToPersist.getFreightNumber());
                freightService.add(freightToPersist);
            } else{
                log.info(path + "already contains, not adding" + freightToPersist);
                route.setFreight(freightService.findByNumber(route.getFreight().getFreightNumber()));
            }
            routeService.add(route);
        }

        log.info("BEFORE ADDING ORDER");
        orderService.add(order);
        log.info("AFTER ADDING ORDER");



        List<Order> orderList = (List<Order>) httpSession.getAttribute("orderList");
        if (orderList == null) {
            orderList = new ArrayList<>();
            orderList.add(order);
        } else {
            orderList.add(order);
        }
        httpSession.setAttribute("orderList", orderList);
    }
}
