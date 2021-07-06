package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageDto getImage(Long id);

    Long save(MultipartFile file);
}
