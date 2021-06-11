package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.CompanyService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @GetMapping("/all")
    public Page<Company> getAll() {
        Pageable pageWithFiveElements = PageRequest.of(0, 5);
        return companyService.findAll(pageWithFiveElements);
    }

    @PostMapping
    public Company save(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Long id, @RequestBody Company company) {
        company.setId(id);
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

    @GetMapping("/{locationId}/companies")
    public Page<Company> getCompaniesByLocation(@PathVariable Long locationId) {
        Pageable pageWithFiveElements = PageRequest.of(0, 5);
        return companyService.findAllByLocationsId(locationId, pageWithFiveElements);
    }
}
