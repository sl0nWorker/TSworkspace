package com.inc.slon.service;

import com.inc.slon.model.Trucker;

import java.util.List;

public interface TruckerService {
    List<Trucker> truckerList();

    void add(Trucker trucker);

    void removeAllById(String[] ids);

    Trucker findById(Long idTrucker);

    void update(Trucker updateTrucker);
}
