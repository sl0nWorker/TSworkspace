package com.inc.slon.service.impl;

import com.inc.slon.dao.FreightDao;
import com.inc.slon.model.Freight;
import com.inc.slon.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FreightServiceImpl implements FreightService {
    @Autowired
    FreightDao freightDao;

    @Transactional
    @Override
    public void add(Freight freight) {
       freightDao.add(freight);
    }
}
