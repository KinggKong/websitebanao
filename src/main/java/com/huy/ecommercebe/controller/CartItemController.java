package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.dto.response.ApiResponse;
import com.huy.ecommercebe.exception.CartItemException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.CartItem;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.service.impl.CartItemServiceImpl;
import com.huy.ecommercebe.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemController {
    UserServiceImpl userService;
    CartItemServiceImpl cartItemService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt
    ) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Delete successfully");
        apiResponse.setStatus(true);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem,
                                                   @PathVariable Long cartItemId,
                                                   @RequestHeader("Authorization") String jwt
    ) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
    }
}
