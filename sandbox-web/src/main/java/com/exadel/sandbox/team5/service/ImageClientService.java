package com.exadel.sandbox.team5.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageClientService {
    Long save(MultipartFile file);
}
