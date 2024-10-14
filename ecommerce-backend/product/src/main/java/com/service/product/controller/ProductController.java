package com.service.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.service.product.model.Product;
import com.service.product.model.ProductDTO;
import com.service.product.services.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(@RequestPart("product") ProductDTO productDTO,
            @RequestPart("files") List<MultipartFile> files) {
        try {
            System.out.println(files.size());
            productService.addProduct(productDTO, files);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Page<Product> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        return productService.findAll(page, size);
    }

    @GetMapping("/category")
    public Page<Product> getByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        return productService.findByCategory(category, page, size);
    }

    @GetMapping("/subcategory")
    public Page<Product> getBySubcategory(
            @RequestParam String subcategory,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        return productService.findByCategory(subcategory, page, size);
    }

    @GetMapping("/{ProductCode}")
    public Product getByProductCode(@PathVariable String ProductCode){
        return productService.findByProductCode(ProductCode);
    }

    @GetMapping("/updateStock")
    public void updateStock(@RequestParam String productCode, @RequestParam Integer quantity){
        productService.UpdateStock(productCode, quantity);
    }

    @GetMapping("/canPurchase")
    public boolean canPurchase(@RequestParam String productCode, @RequestParam Integer quantity){
        return productService.canPurchase(productCode, quantity);
    }

    @GetMapping("/addStock")
    public void addStock(@RequestParam String productCode, @RequestParam Integer quantity){
        productService.addStock(productCode, quantity);
    }

}
