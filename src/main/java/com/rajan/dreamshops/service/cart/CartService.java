package com.rajan.dreamshops.service.cart;

import com.rajan.dreamshops.entity.Cart;
import com.rajan.dreamshops.entity.User;

import java.math.BigDecimal;

public interface CartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
