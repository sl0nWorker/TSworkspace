package com.inc.slon.dao;

import com.inc.slon.model.Trucker;

import java.util.List;

public interface TruckerDao {
    List<Trucker> truckerList();

    void add(Trucker trucker);
}
