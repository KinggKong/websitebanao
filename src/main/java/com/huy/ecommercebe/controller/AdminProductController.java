package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.dto.request.ProductRequest;
import com.huy.ecommercebe.dto.response.ApiResponse;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.service.impl.ProductServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminProductController {
    ProductServiceImpl productService;

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        ApiResponse response = new ApiResponse();
        response.setMessage("product deleted successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody ProductRequest[] req) {
        for (ProductRequest product : req) {
            productService.createProduct(product);
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("product deleted successfully");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }



}
