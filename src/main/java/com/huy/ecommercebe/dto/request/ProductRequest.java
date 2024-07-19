package com.huy.ecommercebe.dto.request;

import com.huy.ecommercebe.model.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String title;
    String description;
    int price;
    int discountedPrice;
    int discountedPercent;
    int quantity;
    String brand;
    String color;
    Set<Size> size = new HashSet<>();
    String imageUrl;
    String topLevelCategory;
    String secondLevelCategory;
    String thirdLevelCategory;
}
