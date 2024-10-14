package com.service.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.service.shopping.Model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{
    Cart findByCodeCart(String codeCart);
}
