package com.service.product.services;

import java.io.IOException;
import java.util.List;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.service.product.model.Image;
import com.service.product.model.Product;
import com.service.product.model.ProductDTO;
import com.service.product.repository.ProductPagRepository;
import com.service.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPagRepository productPagRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public Product addProduct(ProductDTO productDTO, List<MultipartFile> files) throws IOException {
        Product product = new Product();
        List<Image> images = imageService.uploadImage(files);
        System.out.println(productDTO.getColors());
        product.setImages(images);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategory(productDTO.getCategory());
        product.setSubcategory(productDTO.getSubcategory());
        product.setColor(productDTO.getColors());
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(int page, int size) {
        return productPagRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Product> findByCategory(String category, int page, int size) {
        return productPagRepository.findByCategory(category, PageRequest.of(page, size));
    }

    @Override
    public Page<Product> findBySubcategory(String subcategory, int page, int size) {
        return productPagRepository.findBySubcategory(subcategory, PageRequest.of(page, size));
    }

    @Override
    public Product findByProductCode(String productCode) {
        Product product = productPagRepository.findByProductCode(productCode).get();
        return product;
    }

    @Override
    public void UpdateStock(String productCode, Integer quantity) {
        productRepository.updateStock(productCode, quantity);
    }

    @Override
    public boolean canPurchase(String productCode, Integer quantity) {
        return productRepository.canPurchase(productCode, quantity);
    }

    @Override
    public void addStock(String productCode, Integer quantity){
        productRepository.addStock(productCode, quantity);
    }
}
