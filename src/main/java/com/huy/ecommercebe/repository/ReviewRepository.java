package com.huy.ecommercebe.repository;

import com.huy.ecommercebe.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getAllByProduct_Id(Long productId);
}
