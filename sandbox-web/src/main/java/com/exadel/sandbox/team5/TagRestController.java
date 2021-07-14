package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.TagService;
import com.exadel.sandbox.team5.service.export.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagRestController {

    private final TagService tagService;
    private final OrderService orderService;
    private final ExportService exportService;

    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable Long id) {
        return tagService.getById(id);
    }

    @GetMapping()
    public List<TagDto> getAll() {
        return tagService.getAll();
    }

    @PostMapping
    public TagDto save(@RequestBody TagDto entity) {
        return tagService.save(entity);
    }

    @GetMapping("/statistic/orders")
    public Map<String, String> getStatisticByOrders() {
        return orderService.getOrdersByTags();
    }

    @GetMapping("/statistic/downloadCSVOrdersByTag")
    public ResponseEntity getOrdersByTagCSVFile() {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.csv";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(exportService.exportServiceCSV(orderService.getOrdersByTags(), "Tags", "Orders")));
    }

    @GetMapping("/statistic/downloadXLSXOrdersByTag")
    public ResponseEntity getOrdersByTagXLSXFile() {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(new InputStreamResource(exportService.exportServiceXLSX(orderService.getOrdersByTags(), "Tags", "Orders")));
    }
}
