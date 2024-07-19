package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.dto.request.RatingRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Rating;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.service.impl.RatingServiceImpl;
import com.huy.ecommercebe.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    UserServiceImpl userService;
    RatingServiceImpl ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,
                                               @RequestHeader("Authorization") String jwt
    ) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        Rating rating = ratingService.creatRating(req, user);

        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId,
                                                         @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductRating(productId);

        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
}
