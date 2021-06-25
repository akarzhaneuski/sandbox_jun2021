package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService service;

    @GetMapping("/{id}")
    public ImageDto getImage(@PathVariable Long id) throws IOException {
        return service.getImage(id);
    }

    @PostMapping
    public ImageDto saveImage(@RequestBody MultipartFile file) throws IOException {
        ImageDto image = new ImageDto();
        image.setContent(file.getBytes());
        image.setContentType(file.getContentType());
        return service.save(image, file.getName());
    }
}
