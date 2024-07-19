package com.huy.ecommercebe.service;

import com.huy.ecommercebe.dto.request.CartRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Cart;
import com.huy.ecommercebe.model.User;
import org.springframework.stereotype.Service;

@Service
public interface ICartService {
    Cart createCart(User user);

    String addCart(Long userId, CartRequest cartItemRequest) throws ProductException;

    Cart findUserCart(Long userId);
}
