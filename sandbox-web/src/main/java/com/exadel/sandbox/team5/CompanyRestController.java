package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.CompanyDto;
import com.exadel.sandbox.team5.dto.ImageDto;
import com.exadel.sandbox.team5.service.CompanyService;
import com.exadel.sandbox.team5.service.ImageService;
import com.exadel.sandbox.team5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;
    private final OrderService orderService;
    private final ImageService imageService;

    @GetMapping("/{id}")
    public CompanyDto getCompany(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @PostMapping
    public CompanyDto save(@RequestBody CompanyDto company, MultipartFile file) throws IOException {
        ImageDto image = new ImageDto();
        image.setContent(file.getInputStream());
        image.setContentType(file.getContentType());
        company.setImageId(imageService.save(image));
        return companyService.save(company);
    }

    @PutMapping("/{id}")
    public CompanyDto update(@PathVariable Long id, @RequestBody CompanyDto company) {
        company.setId(id);
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

    @GetMapping("/{locationId}/companies")
    public List<CompanyDto> getCompaniesByLocation(@PathVariable Long locationId) {
        return companyService.getCompaniesByLocation(locationId);
    }

    @GetMapping("/statistic")
    public Map<String, String> getStatistic() {
        return orderService.getOrdersByCompanies();
    }
}
