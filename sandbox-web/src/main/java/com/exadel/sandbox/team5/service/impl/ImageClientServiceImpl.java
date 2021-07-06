package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.service.ImageClientService;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageClientServiceImpl implements ImageClientService {
    private final ImageService service;

    @Override
    public Long save(MultipartFile file) {
        ImageDto image = new ImageDto();
        try {
            image.setContent(file.getInputStream());
            String contentType = file.getContentType();
            if (Objects.equals(contentType, "image/png")
                    || Objects.equals(contentType, "image/jpg")
                    || Objects.equals(contentType, "image/jpeg")) {
                image.setContentType(contentType);
            } else {
                throw new InvalidContentTypeException("Invalid image content type!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Incorrect file! Message: " + e.getMessage(), e);
        }
        return service.save(image);
    }
}
