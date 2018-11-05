package com.inc.slon.dao.impl;

import com.inc.slon.dao.FreightDao;
import com.inc.slon.model.Freight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FreightDaoImpl implements FreightDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Freight freight) {
        entityManager.persist(freight);
    }
}
