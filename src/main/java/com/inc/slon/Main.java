package com.inc.slon;

import com.inc.slon.model.Truck;
import com.inc.slon.service.TruckService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        TruckService truckService = (TruckService) ctx.getBean("truckServiceImpl");
        List<Truck> list = truckService.listTrucks();
        System.out.println("User count: " + list.size());

        Truck truck = new Truck();
        truck.setGoodCondition(true);
        truck.setName("first");
        truckService.add(truck);
        System.out.println("Truck inserted!");

        list = truckService.listTrucks();
        for (Truck e : list) {
            System.out.println(e.getName() + " " + e.isGoodCondition());
        }
        System.out.println("User count: " + list.size());
    }
}
