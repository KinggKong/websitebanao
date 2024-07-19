package com.huy.ecommercebe.repository;

import com.huy.ecommercebe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoty extends JpaRepository<Product, Long> {

    @Query("select p from Product p where (p.category.name =:category or :category=' ') and " +
            "((:minPrice is null and :maxPrice is null) or (p.discountedPrice between :minPrice and :maxPrice)) and" +
            "(:minDiscount is null or p.discountedPercent >= :minDiscount) order by " +
            "case when :sort ='price_low' then p.discountedPrice end  asc," +
            "case when :sort ='price_hight' then p.discountedPrice end desc")
    List<Product> filterProduct(@Param("category") String category,
                                @Param("minPrice") Integer minPrice,
                                @Param("maxPrice") Integer maxPrice,
                                @Param("minDiscount") Integer minDiscount,
                                @Param("sort") String sort

    );
}
