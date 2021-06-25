package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService service;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] content = service.getImage(id);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @PostMapping
    public Long saveImage(@RequestBody MultipartFile file) throws IOException {
        ImageDto image = new ImageDto();
        image.setContent(file.getInputStream());
        image.setContentType(file.getContentType());
        image.setName(file.getName());
        return service.save(image);
    }
}
