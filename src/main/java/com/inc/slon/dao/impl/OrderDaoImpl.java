package com.inc.slon.dao.impl;

import com.inc.slon.dao.OrderDao;
import com.inc.slon.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(Order order) {
        entityManager.persist(order);
    }
}
