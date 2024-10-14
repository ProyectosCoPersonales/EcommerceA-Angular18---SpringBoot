package com.service.shopping.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_cart", unique = true)
    private String codeCart;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> CartItems = new ArrayList<>();
    private Integer numItems;
    private Double total;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<CartItem> getCartItems() {
        return CartItems;
    }
    public void setCartItems(List<CartItem> cartItems) {
        CartItems = cartItems;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getCodeCart() {
        return codeCart;
    }
    public void setCodeCart(String codeCart) {
        this.codeCart = codeCart;
    }
    
    @PrePersist
    private void generateCode(){
        this.codeCart = "CART-" + UUID.randomUUID().toString();
    }
    public Integer getNumItems() {
        return numItems;
    }
    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }
}
