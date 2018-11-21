package com.inc.slon.component;

import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;
import com.inc.slon.model.FreightStatus;
import com.inc.slon.model.TruckerStatus;
import com.inc.slon.service.CityService;
import com.inc.slon.service.CountryMapService;
import com.inc.slon.service.FreightStatusService;
import com.inc.slon.service.TruckerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFillerOnStartup implements ApplicationListener<ContextRefreshedEvent> {
    private static boolean firstInit = true;
    @Autowired
    TruckerStatusService truckerStatusService;
    @Autowired
    CityService cityService;
    @Autowired
    CountryMapService countryMapService;
    @Autowired
    FreightStatusService freightStatusService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(firstInit) {
            // truckers statuses init
            TruckerStatus truckerStatusFree = new TruckerStatus();
            truckerStatusFree.setStatus("FREE");
            TruckerStatus truckerStatusWork = new TruckerStatus();
            truckerStatusWork.setStatus("WORK");
            TruckerStatus truckerStatusWheel = new TruckerStatus();
            truckerStatusWheel.setStatus("WHEEL");
            truckerStatusService.add(truckerStatusFree);
            truckerStatusService.add(truckerStatusWork);
            truckerStatusService.add(truckerStatusWheel);

            // cities init
            City spb = new City();
            spb.setCityName("SPB");
            City msc = new City();
            msc.setCityName("Moscow");
            City novgorod = new City();
            novgorod.setCityName("Novgorod");
            cityService.add(spb);
            cityService.add(msc);
            cityService.add(novgorod);

            // distances init
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

            FreightStatus freightStatusPrepared = new FreightStatus();
            freightStatusPrepared.setStatus("Prepared");
            FreightStatus freightStatusShipped = new FreightStatus();
            freightStatusShipped.setStatus("Shipped");
            FreightStatus freightStatusDelivered = new FreightStatus();
            freightStatusDelivered.setStatus("Delivered");
            freightStatusService.add(freightStatusPrepared);
            freightStatusService.add(freightStatusShipped);
            freightStatusService.add(freightStatusDelivered);

            firstInit = false;
        }
    }
}