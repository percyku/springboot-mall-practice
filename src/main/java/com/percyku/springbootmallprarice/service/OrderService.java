package com.percyku.springbootmallprarice.service;


import com.percyku.springbootmallprarice.dto.CreateOrderRequest;
import com.percyku.springbootmallprarice.dto.OrderQueryParams;
import com.percyku.springbootmallprarice.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

}
