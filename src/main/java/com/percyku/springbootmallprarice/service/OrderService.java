package com.percyku.springbootmallprarice.service;


import com.percyku.springbootmallprarice.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
