package com.inc.slon.dao;

import com.inc.slon.model.TruckerHistoryShift;

import java.util.List;

public interface TruckerHistoryShiftDao {
    void add(TruckerHistoryShift truckerHistoryShift);
    TruckerHistoryShift lastTruckerHistoryShiftByTruckerId(Long id);
}
