package com.inc.slon.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ActiveOrderController {
    private static final String ACTIVE_ORDERS_PAGE = "activeOrders";
    @Autowired
    private Logger log;

    @RequestMapping(value = {"/activeOrders"}, method = RequestMethod.GET)
    public String showActiveOrdersPage(ModelMap map) {
        log.info("(/activeOrders, get) start");

        log.info("(/activeOrders, get) end, return ACTIVE_ORDERS_PAGE");
        return ACTIVE_ORDERS_PAGE;
    }
}
