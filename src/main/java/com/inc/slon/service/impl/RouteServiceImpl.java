package com.inc.slon.service.impl;

import com.inc.slon.dao.RouteDao;
import com.inc.slon.model.Route;
import com.inc.slon.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteDao routeDao;

    @Transactional
    @Override
    public void add(Route route) {
        routeDao.add(route);
    }
}
