package com.inc.slon.service.impl;

import com.inc.slon.dao.TruckDao;
import com.inc.slon.model.Truck;
import com.inc.slon.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {
    @Autowired
    private TruckDao truckDao;

    @Transactional
    @Override
    public void add(Truck truck) {
        truckDao.add(truck);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Truck> truckList() {
        return truckDao.truckList();
    }

    @Transactional
    @Override
    public void removeAllById(String[] ids) {
        truckDao.removeAllById(ids);
    }

    @Override
    @Transactional
    public Truck findById(String id) {
        return truckDao.findById(id);
    }

    @Override
    @Transactional
    public void update(Truck truck) {
        truckDao.update(truck);
    }
}
