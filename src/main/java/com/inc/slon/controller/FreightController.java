package com.inc.slon.controller;

import com.inc.slon.service.FreightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FreightController {
    private static final String FREIGHT_STATUS_PAGE = "freightStatus";
    @Autowired
    private Logger log;
    @Autowired
    private FreightService freightService;

    @RequestMapping(value = {"/freightStatus"}, method = RequestMethod.GET)
    public String showActiveOrdersPage(ModelMap map) {
        final String path = "(/freightStatus, get) ";
        log.info(path + "start");
        map.addAttribute("freightList",freightService.freightList());
        log.info(path + "end");
        return FREIGHT_STATUS_PAGE;
    }
}
