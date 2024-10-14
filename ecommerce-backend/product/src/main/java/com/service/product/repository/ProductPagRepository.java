package com.service.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.service.product.model.Product;
import java.util.Optional;

@Repository
public interface ProductPagRepository extends PagingAndSortingRepository<Product, Long> {

    @SuppressWarnings("null")
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByCategory(String category, Pageable pageable);
    Page<Product> findBySubcategory(String subcategory, Pageable pageable);
    Optional<Product> findByProductCode(String productCode);
    
}

