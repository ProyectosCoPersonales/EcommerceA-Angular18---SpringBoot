package com.service.shopping.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.shopping.Model.ProductDTO;

@FeignClient("product")
public interface ProductClient {
    @GetMapping("/api/products/{ProductCode}")
    ProductDTO getByProductCode(@PathVariable String ProductCode);

    @GetMapping("/api/products/updateStock")
    void updateStock(@RequestParam String productCode, @RequestParam Integer quantity);

    @GetMapping("/api/products/canPurchase")
    boolean canPurchase(@RequestParam String productCode, @RequestParam Integer quantity);
}
