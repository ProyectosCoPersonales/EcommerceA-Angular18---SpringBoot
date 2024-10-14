package com.service.product.services;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import com.service.product.model.Product;
import com.service.product.model.ProductDTO;

public interface ProductService {
    Product addProduct(ProductDTO productDTO, List<MultipartFile> files) throws IOException;
    Page<Product> findAll(int page, int size);
    Page<Product> findByCategory(String category, int page, int size);
    Page<Product> findBySubcategory(String subcategory, int page, int size);
    Product findByProductCode(String productCode);
    void UpdateStock(String productCode, Integer quantity);
    boolean canPurchase(String productCode, Integer quantity);
    void addStock(String productCode, Integer quantity);
}
