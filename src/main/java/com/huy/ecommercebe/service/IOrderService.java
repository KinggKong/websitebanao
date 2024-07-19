package com.huy.ecommercebe.service;

import com.huy.ecommercebe.exception.OrderException;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Address;
import com.huy.ecommercebe.model.Order;
import com.huy.ecommercebe.model.User;

import java.util.List;

public interface IOrderService {

    Order createOrder(User user, Address shippingAddress);

    Order findOrderById(Long orderId) throws OrderException;

    List<Order> userOrdersHistory(Long userId) throws OrderException;

    Order placedOrder(Long orderId) throws OrderException;

    Order confirmedOrder(Long orderId) throws OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order canleOrder(Long orderId) throws OrderException;

    List<Order> getAllOrders();

    void deleteOrder(Long orderId) throws OrderException;
}
