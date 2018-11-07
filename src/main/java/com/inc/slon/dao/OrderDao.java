package com.inc.slon.dao;

import com.inc.slon.model.Order;

public interface OrderDao {
    void add(Order order);

    Order findByTruckId(Long truckId);
}
