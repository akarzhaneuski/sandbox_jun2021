package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ImageDto;

import java.util.List;

public interface ImageService {

    ImageDto getImage(String name);

    Long save(ImageDto image);

    List<ImageDto> getAll();
}
