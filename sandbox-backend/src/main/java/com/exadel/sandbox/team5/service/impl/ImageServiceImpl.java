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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;
    private final MapperConverter mapper;
    @Value("${app.bucketNameS3}")
    private final String bucketName;
    private final AmazonS3 s3Client;


    public byte[] getImage(Long id) {
        Image img = imageDAO.findById(id).orElseThrow(NoSuchElementException::new);
        byte[] image;
        try {
            image = s3Client.getObject(bucketName, img.getName()).getObjectContent().readAllBytes();
        } catch (IOException e) {
            throw new AmazonServiceException("File not found");
        }
        return image;
    }

    public Long save(ImageDto image) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            PutObjectRequest request = new PutObjectRequest(bucketName, image.getName(), image.getContent(), metadata);
            request.setMetadata(metadata);
            s3Client.putObject(request);
        } catch (SdkClientException e) {
            throw new AmazonServiceException("File doesn't save");
        }
        Image img = mapper.map(image, Image.class);
        img.setImageURL(bucketName);
        return imageDAO.saveAndFlush(img).getId();
    }
}
