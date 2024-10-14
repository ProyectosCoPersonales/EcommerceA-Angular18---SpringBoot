package com.service.shopping.Service;

import com.service.shopping.Model.Cart;

public interface CartService {
    Cart createCart();
    Cart addToCart(String codeCart, String codeProduct, Integer quantity);
    Cart removeToCart(String codeCart, String codeProduct);
    void clearCart(String codeCart);
    Cart showCart(String codeCart);
}
