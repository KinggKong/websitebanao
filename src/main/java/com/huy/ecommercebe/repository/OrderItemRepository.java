package com.huy.ecommercebe.repository;

import com.huy.ecommercebe.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
