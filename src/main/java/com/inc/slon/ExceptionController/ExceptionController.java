package com.inc.slon.ExceptionController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//TODO: custom exceptions?
@ControllerAdvice
public class ExceptionController {
    @Autowired
    Logger log;

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex){
        log.error("Request: " + request.getRequestURL() + " Threw an Exception: " + ex.getMessage(),ex);
        ModelAndView modelAndView = new ModelAndView("errorGeneral");
        modelAndView.addObject("error",ex.getMessage());
        return modelAndView;
    }
}