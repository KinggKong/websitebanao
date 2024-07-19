package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.dto.request.CartRequest;
import com.huy.ecommercebe.exception.ProductException;
import com.huy.ecommercebe.model.Cart;
import com.huy.ecommercebe.model.CartItem;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.repository.CartRepository;
import com.huy.ecommercebe.service.ICartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements ICartService {
    CartRepository cartRepository;
    CartItemServiceImpl cartItemService;
    ProductServiceImpl productService;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCart(Long userId, CartRequest cartItemRequest) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(cartItemRequest.getProductId());

        CartItem existCartItem = cartItemService.isCartItemExist(cart, product, cartItemRequest.getSize(), userId);
        if (existCartItem == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(cartItem.getQuantity());
            cartItem.setUserId(userId);
            int price = cartItemRequest.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(cartItemRequest.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(cartItem);
        }else{
            int newQuantity = existCartItem.getQuantity() + cartItemRequest.getQuantity();
            existCartItem.setQuantity(newQuantity);
        }
        return "Item Add To Card";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscount(totalPrice - totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        return cartRepository.save(cart);
    }
}
