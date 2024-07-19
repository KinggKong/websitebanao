package com.huy.ecommercebe.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_id")
    String orderId;

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

    LocalDateTime orderDate;

    LocalDateTime deliveryDate;

    @OneToOne
    Address shippingAddress;

    @Embedded
    PaymentDetails paymentDetails = new PaymentDetails();

    double totalPrice;

    Integer totalDiscountedPrice;

    Integer discount;


    String orderStatus;

    int totalItem;

    LocalDateTime createdAt;




}
