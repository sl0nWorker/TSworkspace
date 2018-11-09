package com.inc.slon.dao.impl;

import com.inc.slon.dao.RouteDao;
import com.inc.slon.model.Route;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RouteDaoImpl implements RouteDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Route route) {
        entityManager.persist(route);
    }

    @Override
    public void update(Route route) {
        entityManager.merge(route);
    }

    @Override
    public Route findById(Long id) {
        return entityManager.find(Route.class,id);
    }


}
