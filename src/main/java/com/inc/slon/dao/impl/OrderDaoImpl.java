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

    @Override
    public void removeById(Long id) {
        entityManager.remove(entityManager.find(Order.class,id));
    }

    @Override
    public Order findByTruckId(Long truckId) {
        return entityManager.createQuery(
                "SELECT s FROM Order s WHERE s.truck.id = :truckId", Order.class)
                .setParameter("truckId", truckId)
                .getSingleResult();
    }

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }
}
