package com.huy.ecommercebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    Order order;

    @ManyToOne
    Product product;

    String size;

    int quantity;

    Integer price;

    Integer discountedPrice;

    Long userId;

    LocalDateTime deliveryDate;
}
