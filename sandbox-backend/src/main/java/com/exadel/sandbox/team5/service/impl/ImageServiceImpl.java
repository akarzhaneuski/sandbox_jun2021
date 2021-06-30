package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ImageDAO;
import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.vfs2.*;
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
    private static final String BUCKET_NAME = "exadel-image";
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .withRegion(Regions.EU_WEST_3)
            .build();


    public byte[] getImage(Long id) {
        Image img = imageDAO.findById(id).orElseThrow(NoSuchElementException::new);
        byte[] image;
        try {
            image = s3Client.getObject(BUCKET_NAME, img.getName()).getObjectContent().readAllBytes();
        } catch (IOException e) {
            throw new NoSuchElementException("File not found");
        }
        return image;
    }

    public Long save(ImageDto image) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");
            metadata.addUserMetadata("title", "someTitle");
            PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, image.getName(), image.getContent(), metadata);
            request.setMetadata(metadata);
            s3Client.putObject(request);
        } catch (SdkClientException e) {
            throw new AmazonServiceException("File doesn't save");
        }
        Image img = mapper.map(image, Image.class);
        img.setImageURL(BUCKET_NAME);
        return imageDAO.saveAndFlush(img).getId();
    }
}
