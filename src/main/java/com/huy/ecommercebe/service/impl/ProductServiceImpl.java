package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.dto.request.ProductRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Category;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.model.Size;
import com.huy.ecommercebe.repository.CategoryRepostitoy;
import com.huy.ecommercebe.repository.ProductRepositoty;
import com.huy.ecommercebe.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements IProductService {
    ProductRepositoty productRepositoty;
    UserServiceImpl userService;
    CategoryRepostitoy categoryRepostitoy;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Category topLevel = categoryRepostitoy.findCategoryByName(productRequest.getTopLevelCategory());
        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(productRequest.getTopLevelCategory());
            topLevel = categoryRepostitoy.save(topLevelCategory);
        }

        Category secondLevel = categoryRepostitoy.findByNameAndParentCatgory_Name(productRequest.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(productRequest.getSecondLevelCategory());
            secondLevelCategory.setParentCatgory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepostitoy.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepostitoy.findByNameAndParentCatgory_Name(productRequest.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(productRequest.getThirdLevelCategory());
            thirdLevelCategory.setParentCatgory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepostitoy.save(thirdLevelCategory);
        }

        new Product();
        Product product = Product.builder()
                .title(productRequest.getTitle())
                .color(productRequest.getColor())
                .description(productRequest.getDescription())
                .discountedPrice(productRequest.getDiscountedPrice())
                .discountedPercent(productRequest.getDiscountedPercent())
                .imageUrl(productRequest.getImageUrl())
                .brand(productRequest.getBrand())
                .price(productRequest.getPrice())
                .sizes(productRequest.getSize())
                .quantity(productRequest.getQuantity())
                .category(thirdLevel)
                .createdAt(LocalDateTime.now())
                .build();

        return productRepositoty.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepositoty.deleteById(productId);
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product updateProduct) throws ProductException {
        Product product = findProductById(productId);

        if (updateProduct.getQuantity() != 0) {
            product.setQuantity(updateProduct.getQuantity());
        }
        return null;
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepositoty.findById(productId).orElseThrow(() -> new ProductException("Prodct not found with id:" + productId));
    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return List.of();
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);


        List<Product> products = productRepositoty.filterProduct(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).toList();
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).toList();
            }
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProducts;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoty.findAll();
    }

    @Override
    public Page<Product> getAllProductsMyVersion(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String stock, Pageable pageable) throws ProductException {
        return null;
    }
}
