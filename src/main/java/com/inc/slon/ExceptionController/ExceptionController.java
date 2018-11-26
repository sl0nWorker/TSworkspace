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
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        log.error("Request: " + request.getRequestURL() + " Threw an Exception: " + ex.getMessage(), ex);
        String error = ex.getMessage();
        ModelAndView modelAndView = new ModelAndView("errorGeneral");
        //checks
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            sb.append(stackTraceElements[i].getMethodName() + " ");
        }
        String methods = sb.toString();
        if (methods.contains("addTrucker") || methods.contains("editTrucker")) {
            error = "the personal number of the trucker must be unique";
            log.info("methodsStackTrace (Add/Edit) trucker: " + methods);
        } else if (methods.contains("addTruck") || methods.contains("editTruck")) {
            error = "the registration number of the truck must be unique";
            log.info("methodsStackTrace(Add/Edit) truck: " + methods);
        }
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}