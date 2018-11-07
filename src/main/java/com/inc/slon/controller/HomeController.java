package com.inc.slon.controller;

import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;
import com.inc.slon.model.Truck;
import com.inc.slon.model.TruckerStatus;
import com.inc.slon.service.CityService;
import com.inc.slon.service.CountryMapService;
import com.inc.slon.service.TruckService;
import com.inc.slon.service.TruckerStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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


        //TODO: remove it (Adding 3 default cities for testing)


        log.info("(/home, get) end, return INDEX_PAGE");
        return INDEX_PAGE;
    }

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String showTestPage() {
        return TEST_PAGE;
    }

}
