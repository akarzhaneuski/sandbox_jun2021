package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.TagService;
import com.exadel.sandbox.team5.service.export.ExportOrder;
import com.exadel.sandbox.team5.service.export.ExportTag;
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
    private final ExportTag exportTag;
    private final ExportOrder exportOrder;


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

    @GetMapping("/statistic")
    public Map<String, String> getStatistic() {
        return orderService.getOrdersByTags();
    }

    @GetMapping("/statistic/categories")
    public Map<String, String> getStatisticByCategories(){
        return orderService.getOrdersByCategories();
    }

    @GetMapping("/statistic/downloadCSVOrdersByTag")
    public ResponseEntity getOrdersByTagCSVFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.csv";
        InputStreamResource file = new InputStreamResource(exportTag.ordersByTagCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/statistic/downloadXLSXOrdersByTag")
    public ResponseEntity getOrdersByTagXLSXFile() {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.xlsx";
        InputStreamResource file = new InputStreamResource(exportTag.ordersByTagXLSX());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(file);
    }

    @GetMapping("/statistic/downloadCSVOrdersByCategories")
    public ResponseEntity getOrdersByCategoriesCSVFile() {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByCategories.csv";
        InputStreamResource file = new InputStreamResource(exportOrder.ordersByCategoriesCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/statistic/downloadXLSXOrdersByCategories")
    public ResponseEntity getOrdersByCategoriesXLSXFile() {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByCategories.xlsx";
        InputStreamResource file = new InputStreamResource(exportOrder.ordersByCategoriesXLSX());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(file);
    }
}
