package com.percyku.springbootmallprarice.dao;


import com.percyku.springbootmallprarice.model.Order;
import com.percyku.springbootmallprarice.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Integer createOrder(Integer userId,Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
