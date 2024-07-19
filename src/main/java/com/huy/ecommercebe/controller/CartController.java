package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.dto.request.CartRequest;
import com.huy.ecommercebe.dto.response.ApiResponse;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Cart;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.service.impl.CartServiceImpl;
import com.huy.ecommercebe.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartServiceImpl cartService;
    UserServiceImpl userService;


    @GetMapping("")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> findUserCart(@RequestBody CartRequest cartRequest,
                                                    @RequestHeader("Authorization") String jwt
    ) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCart(user.getId(), cartRequest);

        ApiResponse res = new ApiResponse();
        res.setMessage("item add to cart");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
