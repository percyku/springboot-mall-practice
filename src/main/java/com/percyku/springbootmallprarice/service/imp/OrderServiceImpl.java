package com.percyku.springbootmallprarice.service.imp;


import com.percyku.springbootmallprarice.dao.OrderDao;
import com.percyku.springbootmallprarice.dao.ProductDao;
import com.percyku.springbootmallprarice.dto.BuyItem;
import com.percyku.springbootmallprarice.dto.CreateOrderRequest;
import com.percyku.springbootmallprarice.model.OrderItem;
import com.percyku.springbootmallprarice.model.Product;
import com.percyku.springbootmallprarice.model.User;
import com.percyku.springbootmallprarice.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl implements OrderService {



    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {


        int totalAmount = 0;
        List<OrderItem> orderItemList =new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());



            //total price
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;


            //exchange BuyItem to OrderItem
            OrderItem orderItem =new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);

        }

        //create order
        Integer orderId = orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;
    }
}
