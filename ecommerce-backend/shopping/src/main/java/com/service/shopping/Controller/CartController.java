package com.service.shopping.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.shopping.Model.Cart;
import com.service.shopping.Service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(){
        Cart cart = cartService.createCart();
        System.out.println("Creando un carrito"+cart.getCodeCart());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam String codeCart, @RequestParam String codeProduct, @RequestParam Integer quantity) {
        Cart cart = cartService.addToCart(codeCart, codeProduct, quantity);
        System.out.println(codeCart);
        System.out.println(codeProduct);
        System.out.println(quantity);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestParam String codeCart, @RequestParam String codeProduct) {
        try {
            Cart cart = cartService.removeToCart(codeCart, codeProduct);
            return ResponseEntity.ok(cart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam String codeCart) {
        cartService.clearCart(codeCart);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/show")
    public ResponseEntity<Cart> showCart(@RequestParam String codeCart){
        return ResponseEntity.ok(cartService.showCart(codeCart));
    }
}

