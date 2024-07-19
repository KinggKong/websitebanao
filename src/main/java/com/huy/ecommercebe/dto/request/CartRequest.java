package com.huy.ecommercebe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {

    Long productId;

    String size;

    int quantity;

    Integer price;
}
