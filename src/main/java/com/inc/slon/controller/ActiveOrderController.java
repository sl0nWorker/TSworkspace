package com.inc.slon.controller;

import com.inc.slon.service.ArchiveOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ActiveOrderController {
    private static final String ACTIVE_ORDERS_PAGE = "activeOrders";
    private static final String ARCHIVE_ORDERS_PAGE = "archiveOrders";
    @Autowired
    private Logger log;
    @Autowired
    ArchiveOrderService archiveOrderService;

    @RequestMapping(value = {"/activeOrders"}, method = RequestMethod.GET)
    public String showActiveOrdersPage() {
        log.info("(/activeOrders, get) start");
        log.info("(/activeOrders, get) end, return ACTIVE_ORDERS_PAGE");
        return ACTIVE_ORDERS_PAGE;
    }

    @RequestMapping(value = {"/archiveOrders"}, method = RequestMethod.GET)
    public String showArchiveOrdersPage(ModelMap map) {
        log.info("(/archiveOrders, get) start");
        map.addAttribute("archiveOrderList",archiveOrderService.archiveOrderList());
        log.info("(/archiveOrders, get) end, return ARCHIVE_ORDERS_PAGE");
        return ARCHIVE_ORDERS_PAGE;
    }
}
