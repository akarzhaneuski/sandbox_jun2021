package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ImageDto;

public interface ImageService {

    ImageDto getImage(String name);

    Long save(ImageDto image);
}
