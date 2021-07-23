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

    @PutMapping("/{id}/Favorites/add")
    public String addFavorites(@PathVariable Long id, @RequestBody Set<Long> discountIds) {
        employeeService.addFavorites(id, discountIds);
        return "Favorites was updated";
    }

    @PutMapping("/{id}/Favorites/remove")
    public String removeFavorites(@PathVariable Long id, @RequestBody Set<Long> discountIds) {
        employeeService.removeFavorites(id, discountIds);
        return "Favorites was updated";
    }

    @PutMapping("/{id}/Subscriptions/add")
    public String addSubscriptions(@PathVariable Long id, @RequestBody Set<Long> categoryIds) {
        employeeService.addSubscriptions(id, categoryIds);
        return "Subscriptions was updated";
    }

    @PutMapping("/{id}/Subscriptions/remove")
    public String removeSubscriptions(@PathVariable Long id, @RequestBody Set<Long> categoryIds) {
        employeeService.removeSubscriptions(id, categoryIds);
        return "Subscriptions was updated";
    }
}

