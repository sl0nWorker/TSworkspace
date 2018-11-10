package com.inc.slon.service.impl;

import com.inc.slon.dao.OrderDao;
import com.inc.slon.model.Order;
import com.inc.slon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Transactional
    @Override
    public void add(Order order) {
        orderDao.add(order);
    }

    @Transactional
    @Override
    public void removeById(Long id) {
        orderDao.removeById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Order findByTruckId(Long truckId) {
        return orderDao.findByTruckId(truckId);
    }

    @Transactional(readOnly = true)
    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Transactional
    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
