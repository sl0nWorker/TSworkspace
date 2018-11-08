package com.inc.slon.service.impl;


import com.inc.slon.dao.TruckerHistoryShiftDao;
import com.inc.slon.model.TruckerHistoryShift;
import com.inc.slon.service.TruckerHistoryShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TruckerHistoryShiftServiceImpl implements TruckerHistoryShiftService {
    @Autowired
    TruckerHistoryShiftDao truckerHistoryShiftDao;

    @Transactional
    @Override
    public void add(TruckerHistoryShift truckerHistoryShift) {
        truckerHistoryShiftDao.add(truckerHistoryShift);
    }

    @Transactional(readOnly = true)
    @Override
    public TruckerHistoryShift lastTruckerHistoryShiftByTruckerId(Long id) {
        return truckerHistoryShiftDao.lastTruckerHistoryShiftByTruckerId(id);
    }
}
