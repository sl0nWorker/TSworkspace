package com.inc.slon.service.impl;

import com.inc.slon.dao.TruckerDao;
import com.inc.slon.model.Trucker;
import com.inc.slon.service.TruckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TruckerServiceImpl implements TruckerService {
    @Autowired
    TruckerDao truckerDao;

    @Transactional(readOnly = true)
    @Override
    public List<Trucker> truckerList() {
        return truckerDao.truckerList();
    }

    @Transactional
    @Override
    public void add(Trucker trucker) {
        truckerDao.add(trucker);
    }

    @Transactional
    @Override
    public void removeAllById(String[] ids) {
        truckerDao.removeAllById(ids);
    }

    @Transactional
    @Override
    public Trucker findById(Long idTrucker) {
        return truckerDao.findById(idTrucker);
    }

    @Transactional
    @Override
    public void update(Trucker updateTrucker) {
        truckerDao.update(updateTrucker);
    }
}
