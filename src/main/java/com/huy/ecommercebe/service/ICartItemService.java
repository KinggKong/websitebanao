package com.huy.ecommercebe.service;

import com.huy.ecommercebe.exception.CartItemException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Cart;
import com.huy.ecommercebe.model.CartItem;
import com.huy.ecommercebe.model.Product;

public interface ICartItemService {
    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException,UserException;

    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws CartItemException,UserException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
