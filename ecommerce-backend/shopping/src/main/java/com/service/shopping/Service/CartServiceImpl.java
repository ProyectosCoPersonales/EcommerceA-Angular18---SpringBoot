package com.service.shopping.Service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.service.shopping.Client.ProductClient;
import com.service.shopping.Model.Cart;
import com.service.shopping.Model.CartItem;
import com.service.shopping.Model.ProductDTO;
import com.service.shopping.Repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public CartServiceImpl(CartRepository cartRepository, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
    }

    @Override
    public Cart createCart(){
        Cart cart = new Cart();
        cart = new Cart();
        cart.setCartItems(new ArrayList<>());
        cart.setNumItems(0);
        cart.setTotal(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(String codeCart, String codeProduct, Integer quantity) {
        if (!productClient.canPurchase(codeProduct, quantity)) {
            return null;
        }

        Cart cart = cartRepository.findByCodeCart(codeCart);
        ProductDTO product = productClient.getByProductCode(codeProduct);

        if (cart == null) {
            cart = new Cart();
            cart.setCartItems(new ArrayList<>());
            cart.setNumItems(0);
            cart.setTotal(0.0);
        }

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getCodeProduct().equals(codeProduct))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cart.setNumItems(cart.getNumItems()+quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCodeProduct(codeProduct);
            cartItem.setName(product.getName());
            cartItem.setPrice(product.getPrice());
            cart.setNumItems(cart.getNumItems()+quantity);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        productClient.updateStock(codeProduct, quantity);
        cart.setTotal(cart.getCartItems().stream()
                .mapToDouble(x -> x.getPrice() * x.getQuantity()).sum());

        return cartRepository.save(cart);
    }

    @Override
    public Cart removeToCart(String codeCart, String codeProduct) {
        Cart cart = cartRepository.findByCodeCart(codeCart);
        if (cart == null) {
            throw new IllegalArgumentException("El carrito del usuario no existe.");
        }

        CartItem itemToDelete = cart.getCartItems().stream()
                .filter(item -> item.getCodeProduct().equals(codeProduct))
                .findFirst()
                .orElse(null);

        if (itemToDelete == null) {
            throw new IllegalArgumentException("El producto no está en el carrito.");
        }

        Integer quantityToDelete = itemToDelete.getQuantity();
        cart.getCartItems().remove(itemToDelete);
        cart.setNumItems(cart.getNumItems()-quantityToDelete);
        cart.setTotal(cart.getCartItems().stream()
                .mapToDouble(x -> x.getPrice() * x.getQuantity()).sum());

        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(String codeCart) {
        Cart cart = cartRepository.findByCodeCart(codeCart);
        if (cart != null) {
            cart.setCartItems(new ArrayList<>());
            cart.setNumItems(0);
            cart.setTotal(0.0);
            cartRepository.save(cart);
        }
    }
    @Override
    public Cart showCart(String codeCart){
        return cartRepository.findByCodeCart(codeCart);
    }
}
