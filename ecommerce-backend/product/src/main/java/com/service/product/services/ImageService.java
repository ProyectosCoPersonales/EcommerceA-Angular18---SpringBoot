package com.service.product.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.service.product.model.Image;

public interface ImageService {
    List<Image> uploadImage(List<MultipartFile> files) throws IOException;
}
