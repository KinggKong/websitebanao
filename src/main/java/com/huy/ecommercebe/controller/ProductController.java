package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.service.impl.ProductServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {
    ProductServiceImpl productService;

    @GetMapping("")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            @RequestParam String category,
            @RequestParam List<String> color,
            @RequestParam List<String> size,
            @RequestParam Integer minPrice,
            @RequestParam Integer maxPrice,
            @RequestParam Integer minDiscount,
            @RequestParam String sort,
            @RequestParam String stock,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ) {
        Page<Product> response = productService.getAllProducts(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }


}
