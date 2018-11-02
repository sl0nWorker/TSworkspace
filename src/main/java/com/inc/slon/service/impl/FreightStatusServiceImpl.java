package com.inc.slon.service.impl;

import com.inc.slon.dao.FreightStatusDao;
import com.inc.slon.model.FreightStatus;
import com.inc.slon.service.FreightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreightStatusServiceImpl implements FreightStatusService {

    @Autowired
    FreightStatusDao freightStatusDao;

    @Transactional(readOnly = true)
    @Override
    public FreightStatus findByStatusName(String statusName) {
        return freightStatusDao.findByStatusName(statusName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FreightStatus> statusList() {
        return freightStatusDao.statusList();
    }

    @Transactional
    @Override
    public void add(FreightStatus freightStatus) {
        freightStatusDao.add(freightStatus);
    }
}
