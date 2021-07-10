package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.ReviewDto;
import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import com.exadel.sandbox.team5.service.*;
import com.exadel.sandbox.team5.service.export.ExportDiscount;
import com.exadel.sandbox.team5.service.export.ExportOrder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountRestController {

    private final DiscountService service;
    private final ReviewService reviewService;
    private final QRCodeService qrCodeService;
    private final OrderService orderService;
    private final ImageClientService imageService;
    private final ExportOrder exportOrder;
    private  final ExportDiscount exportDiscount;

    @GetMapping("/{id}")
    public DiscountDto getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<DiscountDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DiscountDto save(@RequestBody DiscountDto entity, MultipartFile file) {
        entity.setImageId(imageService.save(file));
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
    public List<ReviewDto> getReviewsByDiscount(@PathVariable Long discountId) {
        return reviewService.getReviewsByDiscount(discountId);
    }

    @PostMapping("/search")
    public Page<DiscountDto> getByCriteria(@RequestBody DiscountSearchCriteria searchCriteria) {
        return service.getByCriteria(searchCriteria);
    }

    @ApiOperation("Generating QR code with param \"promoCode\"")
    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] generateQRCode(@RequestParam("promoCode") String promoCode) {
        return qrCodeService.generateQRCode(promoCode);
    }

    @GetMapping("/statistic")
    public Map<String, String> getStatistic() {
        return orderService.getOrdersByDiscounts();
    }

    @GetMapping("/statistic/views")
    public Map<String, String> getViewsStatistic() {
        return service.getViewsByDiscounts();
    }

    @PutMapping("/{id}/views")
    public void increaseViews(@PathVariable Long id){
        service.incrementViews(id);
    }

    @GetMapping("/statistic/downloadCSVOrdersByDiscounts")
    public ResponseEntity getOrdersByDiscountsCSVFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByDiscounts.csv";
        InputStreamResource file = new InputStreamResource(exportOrder.ordersByDiscountsCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/statistic/downloadCSVViewsByDiscounts")
    public ResponseEntity getViewsByDiscountsCSVFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_ViewsByDiscounts.csv";
        InputStreamResource file = new InputStreamResource(exportDiscount.viewsByDiscountsCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}

