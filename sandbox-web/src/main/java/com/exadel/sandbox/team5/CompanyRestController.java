package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.CompanyService;

import com.exadel.sandbox.team5.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @GetMapping
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

    @GetMapping("/statistic")
    public Map<String, Long> getStatistic() {
        return orderService.getOrdersByCompanies();
    }
}
