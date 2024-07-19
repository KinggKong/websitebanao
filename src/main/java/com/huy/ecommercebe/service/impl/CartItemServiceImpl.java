package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.exception.CartItemException;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Cart;
import com.huy.ecommercebe.model.CartItem;
import com.huy.ecommercebe.model.Product;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.repository.CartItemRepository;
import com.huy.ecommercebe.repository.CartRepository;
import com.huy.ecommercebe.service.ICartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemServiceImpl implements ICartItemService {
    CartItemRepository cartItemRepository;
    UserServiceImpl userService;
    CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());
        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User requestUser = userService.findUserById(userId);

        if (user.getId().equals(requestUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new UserException("Yout cant't remove another users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if (cartItem.isPresent()) {
            return cartItem.get();
        } else {
            throw new CartItemException("CartItem not found with id :" + cartItemId);
        }
    }
}
