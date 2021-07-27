package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.CategoryDto;
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

    @PutMapping("/favorites/add")
    public boolean addFavorites(@RequestBody Set<Long> discountIds) {
        employeeService.addFavorites(discountIds);
        return true;
    }

    @PutMapping("/favorites/remove")
    public boolean removeFavorites(@RequestBody Set<Long> discountIds) {
        employeeService.removeFavorites(discountIds);
        return true;
    }

    @PutMapping("/subscriptions/add")
    public boolean addSubscriptions(@RequestBody Set<Long> categoryIds) {
        employeeService.addSubscriptions(categoryIds);
        return true;
    }

    @PutMapping("/subscriptions/remove")
    public boolean removeSubscriptions(@RequestBody Set<Long> categoryIds) {
        employeeService.removeSubscriptions(categoryIds);
        return true;
    }

    @PostMapping("/favorites")
    public ResultPage<DiscountDto> getAll(@RequestBody SearchCriteria searchCriteria) {
        return employeeService.getFavorites(searchCriteria);
    }

    @PostMapping("/favorites/{id}")
    public ResponseEntity<?> addFavorite(@PathVariable Long id) {
        employeeService.addFavorite(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/favorites/{id}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long id) {
        employeeService.deleteFavorite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/subscriptions")
    public Set<CategoryDto> getSubscriptions() {
        return employeeService.getSubscriptions();
    }
}

