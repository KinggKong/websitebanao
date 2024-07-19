package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.model.OrderItem;
import com.huy.ecommercebe.repository.OrderItemRepository;
import com.huy.ecommercebe.service.IOrderItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderItemServiceImpl implements IOrderItemService {
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
