package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ImageDto;

import java.io.IOException;

public interface ImageService {

    byte[] getImage(Long id) throws IOException;

    ImageDto save(ImageDto image, String fileName) throws IOException;
}
