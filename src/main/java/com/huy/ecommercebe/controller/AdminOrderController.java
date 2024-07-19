package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.dto.response.ApiResponse;
import com.huy.ecommercebe.exception.OrderException;
import com.huy.ecommercebe.model.Order;
import com.huy.ecommercebe.service.impl.OrderServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminOrderController {

    OrderServiceImpl orderService;

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrdersHandler() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
    }


    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId,
                                                       @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.confirmedOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> ShippedOrderHandler(@PathVariable Long orderId,
                                                     @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> DeliverOrderHandler(@PathVariable Long orderId,
                                                     @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> CancelOrderHandler(@PathVariable Long orderId,
                                                    @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        Order order = orderService.canleOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderId,
                                                          @RequestHeader("Authorization") String jwt
    ) throws OrderException {
        orderService.deleteOrder(orderId);
        ApiResponse response = new ApiResponse();
        response.setMessage("order deleted successfully");
        response.setStatus(true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
