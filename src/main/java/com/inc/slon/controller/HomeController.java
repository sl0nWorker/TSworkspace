package com.inc.slon.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final String INDEX_PAGE = "home/home";
    private static final String TEST_PAGE = "TestJsp";
    @Autowired
    private Logger log;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showIndexPage() {
        log.info("(/home, get) start");
        log.info("(/home, get) end, return INDEX_PAGE");
        return INDEX_PAGE;
    }
    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String showTestPage() {
        return TEST_PAGE;
    }

}
