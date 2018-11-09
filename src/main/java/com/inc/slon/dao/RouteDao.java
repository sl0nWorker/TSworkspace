package com.inc.slon.dao;

import com.inc.slon.model.Route;

public interface RouteDao {
    void add(Route route);
    void update(Route route);

    Route findById(Long id);
}
