package com.inc.slon.dao;

import com.inc.slon.model.Freight;

import java.util.List;

public interface FreightDao {
    void add(Freight freight);

    List<Freight> freightList();

    Freight findByNumber(Integer freightNumber);
}
