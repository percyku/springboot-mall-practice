package com.percyku.springbootmallprarice.service;


import com.percyku.springbootmallprarice.dto.CreateOrderRequest;
import com.percyku.springbootmallprarice.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
