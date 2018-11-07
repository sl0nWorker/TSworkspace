package com.inc.slon.service;

import com.inc.slon.model.Freight;

import java.util.List;

public interface FreightService {
    void add(Freight freight);
    List<Freight> freightList();

    Freight findByNumber(Integer freightNumber);
}
