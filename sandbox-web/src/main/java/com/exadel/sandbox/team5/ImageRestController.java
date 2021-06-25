package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService service;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        byte[] image = service.getImage(id);
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ImageDto saveImage(@RequestBody MultipartFile file) throws IOException {
        ImageDto image = new ImageDto();
        image.setContent(file.getBytes());
        image.setContentType(file.getContentType());
        return service.save(image, file.getName());
    }
}
