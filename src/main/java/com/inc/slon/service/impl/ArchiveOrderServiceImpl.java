package com.inc.slon.service.impl;

import com.inc.slon.dao.ArchiveOrderDao;
import com.inc.slon.model.ArchiveOrder;
import com.inc.slon.service.ArchiveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArchiveOrderServiceImpl implements ArchiveOrderService {
    @Autowired
    ArchiveOrderDao archiveOrderDao;

    @Transactional
    @Override
    public void add(ArchiveOrder archiveOrder) {
        archiveOrderDao.add(archiveOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArchiveOrder> archiveOrderList() {
        return archiveOrderDao.archiveOrderList();
    }
}
