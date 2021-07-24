package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @PutMapping("/{id}/favorites/add")
    public boolean addFavorites(@PathVariable Long id, @RequestBody Set<Long> discountIds) {
        employeeService.addFavorites(id, discountIds);
        return true;
    }

    @PutMapping("/{id}/favorites/remove")
    public boolean removeFavorites(@PathVariable Long id, @RequestBody Set<Long> discountIds) {
        employeeService.removeFavorites(id, discountIds);
        return true;
    }

    @PutMapping("/{id}/subscriptions/add")
    public boolean addSubscriptions(@PathVariable Long id, @RequestBody Set<Long> categoryIds) {
        employeeService.addSubscriptions(id, categoryIds);
        return true;
    }

    @PutMapping("/{id}/subscriptions/remove")
    public boolean removeSubscriptions(@PathVariable Long id, @RequestBody Set<Long> categoryIds) {
        employeeService.removeSubscriptions(id, categoryIds);
        return true;
    }

    @PostMapping("/favorites")
    public ResultPage<DiscountDto> getAll(@RequestBody SearchCriteria searchCriteria) {
        return employeeService.getFavorites(searchCriteria);
    }

    @PostMapping("/favorites/add")
    public ResponseEntity<?> addFavorite(@RequestBody Long id) {
        employeeService.addFavorite(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

