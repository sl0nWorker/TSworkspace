package com.inc.slon.controller;

import com.inc.slon.model.*;
import com.inc.slon.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class HomeController {
    @Autowired
    private TruckService truckService;
    @Autowired
    private TruckerStatusService truckerStatusService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryMapService countryMapService;
    @Autowired
    private TruckerHistoryShiftService truckerHistoryShiftService;

    private static final String INDEX_PAGE = "home/home";
    private static final String TEST_PAGE = "TestJsp";
    @Autowired
    private Logger log;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showIndexPage(ModelMap map) {
        log.info("(/home, get) start");

        //TODO: remove it (Adding 3 default status for testing)
        if (truckerStatusService.truckerStatusList().size() == 0) {
            TruckerStatus truckerStatusFree = new TruckerStatus();
            truckerStatusFree.setStatus("FREE");
            TruckerStatus truckerStatusWork = new TruckerStatus();
            truckerStatusWork.setStatus("WORK");
            TruckerStatus truckerStatusWheel = new TruckerStatus();
            truckerStatusWheel.setStatus("WHEEL");
            truckerStatusService.add(truckerStatusFree);
            truckerStatusService.add(truckerStatusWork);
            truckerStatusService.add(truckerStatusWheel);

            /*
            //TODO: remove it (test TruckerShiftStatus)
            GregorianCalendar firstDate = new GregorianCalendar();
            TruckerHistoryShift kurishevStart = new TruckerHistoryShift(1L,"oleg",
                    "kurishev",2255,"StartShift", firstDate);
            truckerHistoryShiftService.add(kurishevStart);
            log.info("/home, get add first date: " + kurishevStart.getShiftStatusTime());

            GregorianCalendar secondDate = new GregorianCalendar();
            TruckerHistoryShift kurishevStart2 = new TruckerHistoryShift(1L,"oleg2",
                    "kurishev2",2255,"StartShift",secondDate);
            truckerHistoryShiftService.add(kurishevStart2);
            log.info("/home, get add second date: "+ kurishevStart2.getShiftStatusTime());

            if (secondDate.after(firstDate)){
                log.info("calendar.after works fine");
            }

            TruckerHistoryShift last = truckerHistoryShiftService.lastTruckerHistoryShiftByTruckerId(1L);
            log.info("/home, get last date: " + last.getFirstName() + last.getLastName());
            */
        }

        //TODO: remove it (Adding 3 default cities for testing)
        log.info("cityList size: " + cityService.cityList().size());
        if (cityService.cityList().size() == 0) {
            City spb = new City();
            spb.setCityName("SPB");
            City msc = new City();
            msc.setCityName("Moscow");
            City novgorod = new City();
            novgorod.setCityName("Novgorod");
            cityService.add(spb);
            cityService.add(msc);
            cityService.add(novgorod);
            //TODO: remove it (Adding  default countryMap for testing)
            if (countryMapService.countryMapList() == null || countryMapService.countryMapList().size() == 0) {
                CountryMap spbMsc = new CountryMap(spb, msc, 710);
                CountryMap spbNovogorod = new CountryMap(spb, novgorod, 1127);
                CountryMap mscSpb = new CountryMap(msc, spb, 710);
                CountryMap mscNovgorod = new CountryMap(msc, novgorod, 417);
                CountryMap novgorodSpb = new CountryMap(novgorod, spb, 1127);
                CountryMap novgorodMsc = new CountryMap(novgorod, msc, 417);
                countryMapService.add(spbMsc);
                countryMapService.add(spbNovogorod);
                countryMapService.add(mscSpb);
                countryMapService.add(mscNovgorod);
                countryMapService.add(novgorodSpb);
                countryMapService.add(novgorodMsc);
            }
        }





        log.info("(/home, get) end, return INDEX_PAGE");
        return INDEX_PAGE;
    }

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String showTestPage() {
        return TEST_PAGE;
    }

}
