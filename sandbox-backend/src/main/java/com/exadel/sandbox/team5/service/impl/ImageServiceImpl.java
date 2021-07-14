package com.exadel.sandbox.team5.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.exadel.sandbox.team5.dao.ImageDAO;
import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;
    private final MapperConverter mapper;

    @Value("${app.bucketNameS3}")
    private String bucketName;

    private final AmazonS3 s3Client;


    public ImageDto getImage(String name) {
        Image img = imageDAO.findImageByName(name).orElseThrow(NoSuchElementException::new);
        ImageDto image = mapper.map(img, ImageDto.class);
        image.setContent(s3Client.getObject(bucketName, img.getName()).getObjectContent());
        return image;
    }

    public Long save(ImageDto image) {
        Image img = mapper.map(image, Image.class);
        String imageName = parseImageName(image, UUID.randomUUID().toString());
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            PutObjectRequest request = new PutObjectRequest(bucketName, imageName, image.getContent(), metadata);
            s3Client.putObject(request);
        } catch (SdkClientException e) {
            throw new AmazonServiceException("File doesn't save! Message:  " + e.getMessage(), e);
        }
        img.setImageURL(bucketName);
        img.setName(imageName);
        return imageDAO.save(img).getId();
    }

    @Override
    public List<ImageDto> getAll() {
        var images = imageDAO.findAll();
        return mapper.mapAll(images, ImageDto.class);
    }

    private String parseImageName(ImageDto image, String name) {
        String typeFile = image.getContentType().split("/")[1];
        return String.format("%s.%s", name, typeFile);
    }
}
