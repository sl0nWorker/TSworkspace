package com.inc.slon.dao;

import com.inc.slon.model.Order;

public interface OrderDao {
    void add(Order order);

    void removeById(Long id);

    Order findByTruckId(Long truckId);

    Order findById(Long id);

    void update(Order order);
}
