package com.inc.slon.service;

import com.inc.slon.model.TruckerHistoryShift;

public interface TruckerHistoryShiftService {
    void add(TruckerHistoryShift truckerHistoryShift);
    TruckerHistoryShift lastTruckerHistoryShiftByTruckerId(Long id);
}
