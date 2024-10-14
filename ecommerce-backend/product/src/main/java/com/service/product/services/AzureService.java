package com.service.product.services;

import org.springframework.web.multipart.MultipartFile;

public interface AzureService {
    String uploadToBlobStorage(MultipartFile file);
}
