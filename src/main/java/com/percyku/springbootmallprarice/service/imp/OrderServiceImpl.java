package com.percyku.springbootmallprarice.service.imp;


import com.percyku.springbootmallprarice.dao.OrderDao;
import com.percyku.springbootmallprarice.dao.ProductDao;
import com.percyku.springbootmallprarice.dao.UserDao;
import com.percyku.springbootmallprarice.dto.BuyItem;
import com.percyku.springbootmallprarice.dto.CreateOrderRequest;
import com.percyku.springbootmallprarice.model.Order;
import com.percyku.springbootmallprarice.model.OrderItem;
import com.percyku.springbootmallprarice.model.Product;
import com.percyku.springbootmallprarice.model.User;
import com.percyku.springbootmallprarice.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order =orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList =orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        //check user exist or not
        User user = userDao.getUserById(userId);

        if(user == null){
            log.warn("This userId {} is not exist",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;
        List<OrderItem> orderItemList =new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());


            //check product exist or not, and check product is enough or not
            if(product == null){
                log.warn("商品 {} 不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(),product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除商品庫存
            productDao.updateStock(product.getProductId(),product.getStock() - buyItem.getQuantity());


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
