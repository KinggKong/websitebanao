package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.dto.request.ReviewRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.model.Review;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.repository.ProductRepositoty;
import com.huy.ecommercebe.repository.ReviewRepository;
import com.huy.ecommercebe.service.IReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements IReviewService {
    ReviewRepository reviewRepository;
    ProductServiceImpl productService;
    ProductRepositoty productRepositoty;

    @Override
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException {
        Product product = new Product();

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(review.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllByProduct_Id(productId);
    }
}
