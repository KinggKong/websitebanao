package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.dto.request.ReviewRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Review;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.service.impl.ReviewServiceImpl;
import com.huy.ecommercebe.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {
    ReviewServiceImpl reviewService;
    UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt
    ) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(req, user);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReview(@PathVariable Long productId
    ) {
        List<Review> reviews = reviewService.getAllReviews(productId);
        return new ResponseEntity<>(reviews, HttpStatus.ACCEPTED);
    }

}
