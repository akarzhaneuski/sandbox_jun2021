package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @GetMapping("/statistic/download")
    public ResponseEntity getFile(HttpServletRequest request) throws IOException {

        String filename = "report_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) + "_OrdersByTags.csv";
        InputStreamResource file = new InputStreamResource(tagService.getStatisticFile());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
