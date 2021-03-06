package com.inc.slon.dao;

import com.inc.slon.model.FreightStatus;

import java.util.List;

public interface FreightStatusDao {
    FreightStatus findByStatusName(String statusName);

    List<FreightStatus> statusList();

    void add(FreightStatus freightStatus);
}
