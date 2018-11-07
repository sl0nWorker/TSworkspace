package com.inc.slon.service;

import com.inc.slon.model.Trucker;
import com.inc.slon.model.TruckerStatus;

import java.util.List;

public interface TruckerStatusService {
    List<TruckerStatus> truckerStatusList();

    void add(TruckerStatus truckerStatus);

    TruckerStatus findById(String id);

    TruckerStatus findByName(String status);
}
