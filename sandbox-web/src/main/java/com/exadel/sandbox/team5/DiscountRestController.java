package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import com.exadel.sandbox.team5.service.*;
import com.exadel.sandbox.team5.service.export.ExportService;
import com.exadel.sandbox.team5.util.ResultPage;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
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
    private final ExportService exportService;

    @GetMapping("/{id}")
    public DiscountDto getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    //fixme delete this method?
    @GetMapping
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

    @PutMapping("/{id}/uploadImage")
    public DiscountDto updateImage(@PathVariable Long id, @RequestBody MultipartFile file) {
        DiscountDto discount = service.getById(id);
        discount.setNameImage(imageService.save(file));
        return service.update(discount);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{discountId}/reviews")
    public Map<Integer, Integer> getReviewsByDiscount(@PathVariable Long discountId) {
        return reviewService.getReviewsByDiscount(discountId);
    }

    @PostMapping("/search")
    public ResultPage<DiscountDto> getByCriteria(@RequestBody DiscountSearchCriteria searchCriteria) {
        return service.getByCriteria(searchCriteria);
    }

    @ApiOperation("Generating QR code with param \"promoCode\"")
    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] generateQRCode(@RequestParam("promoCode") String promoCode) {
        return qrCodeService.generateQRCode(promoCode);
    }

    @GetMapping("/statistic/orders")
    public Map<String, String> getStatisticByOrders() {
        return orderService.getOrdersByDiscounts();
    }

    @GetMapping("/statistic/views")
    public Map<String, String> getStatisticByViews() {
        return service.getViewsByDiscounts();
    }

    @PutMapping("/{id}/views")
    public ResponseEntity increaseViews(@PathVariable Long id) {
        service.incrementViews(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/statistic/downloadCSVOrdersByDiscounts")
    public ResponseEntity getOrdersByDiscountsCSVFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByDiscounts.csv";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(exportService.exportServiceCSV(orderService.getOrdersByDiscounts(), "Discounts", "Orders")));
    }

    @GetMapping("/statistic/downloadXLSXOrdersByDiscounts")
    public ResponseEntity getOrdersByDiscountsXLSXFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByDiscounts.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(new InputStreamResource(exportService.exportServiceXLSX(orderService.getOrdersByDiscounts(), "Discounts", "Orders")));
    }

    @GetMapping("/statistic/downloadCSVViewsByDiscounts")
    public ResponseEntity getViewsByDiscountsCSVFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_ViewsByDiscounts.csv";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(exportService.exportServiceCSV(service.getViewsByDiscounts(), "Discounts", "Views")));
    }

    @GetMapping("/statistic/downloadXLSXViewsByDiscounts")
    public ResponseEntity getViewsByDiscountsXLSXFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_ViewsByDiscounts.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(new InputStreamResource(exportService.exportServiceXLSX(service.getViewsByDiscounts(), "Discounts", "Views")));
    }
}

