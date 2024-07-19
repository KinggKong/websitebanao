package com.huy.ecommercebe.service;


import com.huy.ecommercebe.dto.request.ProductRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductRequest productRequest);

    String deleteProduct(Long ProductId) throws ProductException;

    Product updateProduct(Long ProductId, Product product) throws ProductException;

    Product findProductById(Long ProductId) throws ProductException;

    List<Product> findProductByCategory(String category) throws ProductException;

    Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

    List<Product> findAllProducts();

    Page<Product> getAllProductsMyVersion(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String stock, Pageable pageable) throws ProductException;
}
