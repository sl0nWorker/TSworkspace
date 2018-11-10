package com.inc.slon.service;

import com.inc.slon.model.ArchiveOrder;

import java.util.List;

public interface ArchiveOrderService {
    void add(ArchiveOrder archiveOrder);
    List<ArchiveOrder> archiveOrderList();
}
