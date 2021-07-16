package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.TagService;
import com.exadel.sandbox.team5.service.export.ExportService;
import com.exadel.sandbox.team5.service.export.FileNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagRestController {

    private final TagService tagService;
    private final OrderService orderService;
    private final ExportService exportService;
    private final FileNameGenerator fileNameGenerator;


    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable Long id) {
        return tagService.getById(id);
    }

    @GetMapping()
    public List<TagDto> getAll() {
        return tagService.getAll();
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping
    public TagDto save(@RequestBody TagDto entity) {
        return tagService.save(entity);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/statistic/orders")
    public Map<String, String> getStatisticByOrders() {
        return orderService.getOrdersByTags();
    }

    @GetMapping("/statistic/downloadCSVOrdersByTag")
    public ResponseEntity getOrdersByTagCSVFile() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileNameGenerator.csvFileNameGenerator("OrdersByTags"))
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(exportService.exportServiceCSV(orderService.getOrdersByTags(), "Tags", "Orders")));
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/statistic/downloadXLSXOrdersByTag")
    public ResponseEntity getOrdersByTagXLSXFile() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileNameGenerator.xlsxFileNameGenerator("OrdersByTags"))
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(new InputStreamResource(exportService.exportServiceXLSX(orderService.getOrdersByTags(), "Tags", "Orders")));
    }
}
