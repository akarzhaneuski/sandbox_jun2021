package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ImageDto;

import java.io.IOException;

public interface ImageService {

    byte[] getImage(Long id);

    Long save(ImageDto image);
}
