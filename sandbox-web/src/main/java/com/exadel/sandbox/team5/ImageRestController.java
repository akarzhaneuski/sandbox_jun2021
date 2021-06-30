package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        ImageDto image = service.getImage(id);
        byte[] content = image.getContent().readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getContentType()));
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @PostMapping
    public Long saveImage(@RequestBody MultipartFile file) throws IOException {
        ImageDto image = new ImageDto();
        image.setContent(file.getInputStream());
        image.setContentType(file.getContentType());
        return service.save(image);
    }
}
