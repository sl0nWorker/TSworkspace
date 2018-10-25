package com.inc.slon.dao;

import com.inc.slon.model.Truck;

import java.util.List;

public interface TruckDao {
    void add(Truck truck);

    List<Truck> truckList();

    void removeAllById(String[] ids);
}
