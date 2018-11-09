package com.inc.slon.service;

import com.inc.slon.model.Route;

public interface RouteService {
    void add(Route route);
    void update(Route route);
    Route findById(Long id);
}
