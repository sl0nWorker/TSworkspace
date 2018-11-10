package com.inc.slon.dao;

import com.inc.slon.model.ArchiveOrder;

import java.util.List;

public interface ArchiveOrderDao {
    void add(ArchiveOrder archiveOrder);
    List<ArchiveOrder> archiveOrderList();
}
