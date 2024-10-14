package com.service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.service.product.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long>{
    
}
