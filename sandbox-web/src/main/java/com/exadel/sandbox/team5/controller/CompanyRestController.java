package com.exadel.sandbox.team5.controller;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.CompanyService;

import lombok.RequiredArgsConstructor;

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
    public List<Company> getAll() {
        return companyService.getAll();
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
    public List<Company> getCompaniesByLocation(@PathVariable Long locationId) {
        return companyService.getCompaniesByLocation(locationId);
    }
}
