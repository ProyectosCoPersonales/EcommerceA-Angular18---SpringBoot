package com.service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.service.product.model.Product;
import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    @Transactional  
    @Modifying  
    @Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.productCode = :productCode AND p.stock >= :quantity")  
    void updateStock(String productCode, Integer quantity);

    @Transactional  
    @Modifying  
    @Query("UPDATE Product p SET p.stock = p.stock + :quantity WHERE p.productCode = :productCode")
    void addStock(String productCode, Integer quantity);
    

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +  
           "FROM Product p WHERE p.productCode = :productCode AND p.stock >= :quantity")  
    boolean canPurchase(String productCode, Integer quantity);
}
