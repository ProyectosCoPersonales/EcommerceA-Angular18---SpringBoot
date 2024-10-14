package com.service.product.model;

import java.util.List;
import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String subcategory;
    private List<String> colors;
}

