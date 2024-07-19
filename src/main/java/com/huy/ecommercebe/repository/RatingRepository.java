package com.huy.ecommercebe.repository;

import com.huy.ecommercebe.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> getAllByProduct_Id(Long productId);
}
