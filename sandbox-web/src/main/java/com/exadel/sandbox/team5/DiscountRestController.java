package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountRestController {

    private final DiscountService service;
    private String upLoadPath;

    @GetMapping("/{id}")
    public Discount getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Discount> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Discount save(@RequestParam("file") MultipartFile file,
            @RequestBody Discount entity) throws IOException {

        if(file != null && !file.getOriginalFilename().isEmpty()){
            File upLoadDir = new File(upLoadPath);
            String fileName = file.getOriginalFilename();
            if(!upLoadDir.exists()){
                upLoadDir.mkdir();
            }
            file.transferTo(new File(upLoadPath + "/" + fileName));
            entity.setFileName(fileName);
        }
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public Discount update(@PathVariable Long id, @RequestBody Discount entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

