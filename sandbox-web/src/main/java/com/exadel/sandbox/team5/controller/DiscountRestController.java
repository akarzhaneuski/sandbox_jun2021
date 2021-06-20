package com.exadel.sandbox.team5.controller;

import com.exadel.sandbox.team5.barcodes.QRCodeGenerator;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
@Log4j2
public class DiscountRestController {

    private final DiscountService service;
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public DiscountDto getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<DiscountDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DiscountDto save(@RequestBody DiscountDto entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public DiscountDto update(@PathVariable Long id, @RequestBody DiscountDto entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{discountId}/reviews")
    public List<Review> getReviewsByDiscount(@PathVariable Long discountId) {
        return reviewService.getReviewsByDiscount(discountId);
    }

    @ApiOperation("Generating QR code with text \"Exadel employee. Special discount\"")
    @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> generateQRCode() {
        try (var baos = new ByteArrayOutputStream()) {
            var image = QRCodeGenerator.generateQRCodeImage("Exadel employee. Special discount");
            ImageIO.write(image, "png", baos);
            return ResponseEntity.ok(baos.toByteArray());
        } catch (Exception e) {
            log.error("There was an error during barcode generation", e);
            return ResponseEntity.badRequest().build();
        }
    }
}

