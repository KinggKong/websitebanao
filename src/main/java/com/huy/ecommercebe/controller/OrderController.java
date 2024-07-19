package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.exception.OrderException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Address;
import com.huy.ecommercebe.model.Order;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.service.impl.OrderServiceImpl;
import com.huy.ecommercebe.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderServiceImpl orderService;
    UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt
    ) throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);

        List<Order> orders = orderService.userOrdersHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") Long orderId,
                                               @RequestHeader("Authorization") String jwt
    ) throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);

        Order order = orderService.findOrderById(orderId);

        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

}
