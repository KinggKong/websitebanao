package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.dto.request.RatingRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.model.Rating;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.repository.RatingRepository;
import com.huy.ecommercebe.service.IRatingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RatingServiceImpl implements IRatingService {
    RatingRepository ratingRepository;
    ProductServiceImpl productService;

    @Override
    public Rating creatRating(RatingRequest ratingRequest, User user) throws ProductException {
        Product product = productService.findProductById(ratingRequest.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(ratingRequest.getRating());
        rating.setCreateAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {
        return ratingRepository.getAllByProduct_Id(productId);
    }
}
