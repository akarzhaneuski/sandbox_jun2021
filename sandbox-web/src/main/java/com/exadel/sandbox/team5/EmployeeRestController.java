package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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
}

