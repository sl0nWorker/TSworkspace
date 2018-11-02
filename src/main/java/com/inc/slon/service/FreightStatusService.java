package com.inc.slon.service;

import com.inc.slon.model.FreightStatus;

import java.util.List;

public interface FreightStatusService {
    FreightStatus findByStatusName(String statusName);

    List<FreightStatus> statusList();

    void add(FreightStatus freightStatus);
}
