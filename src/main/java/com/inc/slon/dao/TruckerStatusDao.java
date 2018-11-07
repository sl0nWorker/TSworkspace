package com.inc.slon.dao;

import com.inc.slon.model.TruckerStatus;

import java.util.List;

public interface TruckerStatusDao {
    List<TruckerStatus> truckerStatusList();

    void add(TruckerStatus truckerStatus);

    TruckerStatus findById(String id);

    TruckerStatus findByName(String status);
}
