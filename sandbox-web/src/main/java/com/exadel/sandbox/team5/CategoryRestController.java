package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.CategoryDtoWithoutId;
import com.exadel.sandbox.team5.service.CategoryService;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.export.ExportService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;
    private final ExportService exportService;
    private final OrderService orderService;

    @GetMapping("/getAll")
    List<CategoryDto> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("/save")
    CategoryDto save(@RequestBody CategoryDtoWithoutId categoryDto){
        return categoryService.save(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @ApiOperation("Statistic of Orders By Categories")
    @GetMapping("/statistic/categories")
    public Map<String, String> getStatisticOrdersByCategories() {
        return orderService.getOrdersByCategories();
    }

    @GetMapping("/statistic/downloadCSVOrdersByCategories")
    public ResponseEntity getOrdersByCategoriesCSVFile() {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByCategories.csv";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(new InputStreamResource(exportService.exportServiceCSV(orderService.getOrdersByCategories(), "Categories", "Orders")));
    }

    @GetMapping("/statistic/downloadXLSXOrdersByCategories")
    public ResponseEntity getOrdersByCategoriesXLSXFile() {
        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByCategories.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(new InputStreamResource(exportService.exportServiceXLSX(orderService.getOrdersByCategories(), "Categories", "Orders")));
    }
}
