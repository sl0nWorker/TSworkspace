package com.inc.slon.service.impl;

import com.inc.slon.dao.TruckerStatusDao;
import com.inc.slon.model.TruckerStatus;
import com.inc.slon.service.TruckerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TruckerStatusServiceImpl implements TruckerStatusService {
    @Autowired
    TruckerStatusDao truckerStatusDao;

    @Transactional(readOnly = true)
    @Override
    public List<TruckerStatus> truckerStatusList() {
        return truckerStatusDao.truckerStatusList();
    }

    @Transactional
    @Override
    public void add(TruckerStatus truckerStatus) {
        truckerStatusDao.add(truckerStatus);
    }

    @Transactional
    @Override
    public TruckerStatus findById(String id) {
        return truckerStatusDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public TruckerStatus findByName(String status) {
        return truckerStatusDao.findByName(status);
    }

}

