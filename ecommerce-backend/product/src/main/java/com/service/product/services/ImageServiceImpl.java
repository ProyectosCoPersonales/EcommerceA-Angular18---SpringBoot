package com.service.product.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.product.model.Image;
import com.service.product.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
    private final AzureService azureService;

    private final ImageRepository imageRepository;

    public ImageServiceImpl(AzureService azureService, ImageRepository imageRepository) {
        this.azureService = azureService;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> uploadImage(List<MultipartFile> files) throws IOException {
        List<Image> images = new ArrayList<>();
        System.out.println("Archivos recibidos: " + files.size());
        for (int i = 0; i < files.size(); i++) {
            System.out.println("Procesando archivo: " + files.get(i).getOriginalFilename());
            Image image = new Image();
            image.setImageUrl(azureService.uploadToBlobStorage(files.get(i)));
            image.setName(files.get(i).getOriginalFilename());
            images.add(image);
            imageRepository.save(image);
        }
        return images;
    }

}
