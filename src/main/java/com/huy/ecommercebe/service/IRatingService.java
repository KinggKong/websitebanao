package com.huy.ecommercebe.service;

import com.huy.ecommercebe.dto.request.RatingRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Rating;
import com.huy.ecommercebe.model.User;

import java.util.List;

public interface IRatingService {
    Rating creatRating(RatingRequest ratingRequest, User user) throws ProductException;

    List<Rating> getProductRating(Long productId);
}
