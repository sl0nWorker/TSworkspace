package com.inc.slon.service.impl;

import com.inc.slon.dao.FreightDao;
import com.inc.slon.model.Freight;
import com.inc.slon.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreightServiceImpl implements FreightService {
    @Autowired
    FreightDao freightDao;

    @Transactional
    @Override
    public void add(Freight freight) {
       freightDao.add(freight);
    }

    @Transactional
    @Override
    public void update(Freight freight) {
        freightDao.update(freight);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Freight> freightList() {
        return freightDao.freightList();
    }

    @Transactional(readOnly = true)
    @Override
    public Freight findById(Long id) {
        return freightDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Freight findByNumber(Integer freightNumber) {
        return freightDao.findByNumber(freightNumber);
    }
}
