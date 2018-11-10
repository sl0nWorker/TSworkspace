package com.inc.slon.service;

import com.inc.slon.model.Order;

public interface OrderService {
    void add(Order order);

    void removeById(Long id);

    Order findByTruckId(Long truckId);

    Order findById(Long id);

    void update(Order order);
}
