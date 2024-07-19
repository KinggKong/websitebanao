package com.huy.ecommercebe.service;

import com.huy.ecommercebe.dto.request.ReviewRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Review;
import com.huy.ecommercebe.model.User;

import java.util.List;

public interface IReviewService {
    Review createReview(ReviewRequest reviewRequest, User user) throws ProductException;

    List<Review> getAllReviews(Long productId);
}
