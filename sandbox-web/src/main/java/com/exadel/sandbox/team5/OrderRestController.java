package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.export.ExportOrder;
import com.exadel.sandbox.team5.util.CreateOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final ExportOrder exportOrder;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto order) {
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @GetMapping("/invalidate")
    public OrderDto invalidate(@PathVariable Long discountId, @PathVariable String promoCode) {
        return orderService.invalidatePromoCode(discountId, promoCode);
    }

    @PutMapping("/create")
    public OrderDto create(@RequestBody CreateOrder createOrder) {
        if (createOrder != null && createOrder.getAmountDiscountDays() == 0) {
            createOrder.setAmountDiscountDays(7);
        }
        return orderService.createOrder(createOrder);
    }

    @GetMapping("/statistic/downloadCSVOrdersByCategories")
    public ResponseEntity getOrdersByCategoriesCSVFile(HttpServletRequest request) {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.csv";
        InputStreamResource file = new InputStreamResource(exportOrder.ordersByCategoriesCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/statistic/downloadXLSXOrdersByCategories")
    public ResponseEntity getOrdersByCategoriesXLSXFile(HttpServletRequest request) {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.csv";
        InputStreamResource file = new InputStreamResource(exportOrder.ordersByCategoriesCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
