package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticRestController {

    private final OrderService orderService;

    @GetMapping("/for/discounts")
    public Map<String, Long> getAllByDiscounts() {
        return orderService.getOrdersByDiscounts();
    }

    @GetMapping("/for/companies")
    public Map<String, Long> getAllByCompanies() {
        return orderService.getOrdersByCompanies();
    }

    @GetMapping("/for/tags")
    public Map<String, Long> getAllByTags() {
        return orderService.getOrdersByTags();
    }
}
