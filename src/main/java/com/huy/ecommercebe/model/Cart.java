package com.huy.ecommercebe.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    Set<CartItem> cartItems;

    @Column(name = "total_price")
    double totalPrice;

    @Column(name = "total_item")
    int totalItem;

    int totalDiscountedPrice;

    int totalDiscount;
}
