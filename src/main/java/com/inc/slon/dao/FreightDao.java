package com.inc.slon.dao;

import com.inc.slon.model.Freight;

import java.util.List;

public interface FreightDao {
    void add(Freight freight);

    void update(Freight freight);

    List<Freight> freightList();

    Freight findById(Long id);

    Freight findByNumber(Integer freightNumber);
}
