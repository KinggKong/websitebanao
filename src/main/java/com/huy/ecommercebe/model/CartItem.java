package com.huy.ecommercebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    Cart cart;

    @ManyToOne
    Product product;

    String size;

    int quantity;

    Integer price;

    Integer discountedPrice;

    Long userId;
}
