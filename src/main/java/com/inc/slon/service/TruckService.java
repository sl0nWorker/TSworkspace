package com.inc.slon.service;

import com.inc.slon.model.Truck;

import java.util.List;

public interface TruckService {
    void add(Truck truck);

    List<Truck> truckList();

    void removeAllById(String[] ids);

    Truck findById(String id);

    void update(Truck truck);
}
