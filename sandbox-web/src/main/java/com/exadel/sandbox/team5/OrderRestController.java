package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.util.OrderCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final MapperConverter mapper;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id){
        return orderService.getById(id);
    }

    @GetMapping("/all")
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto order){
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto order){
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @PutMapping("/invalidate")
    public OrderDto invalidate(@RequestBody OrderDto order) {
        return orderService.invalidatePromoCode(order.getDiscount().getId(),order.getEmployeePromocode());
    }

    @PutMapping("/create")
    public OrderDto create(@RequestBody OrderCriteria criteria){
        return orderService.createOrder(criteria);
    }

    @GetMapping("/statistic/byDiscounts")
    public Map<String, Long> getAllByDiscounts() {
        return orderService.getOrdersByDiscounts();
    }

    @GetMapping("/statistic/byCompanies")
    public Map<String, Long> getAllByCompanies() {
        return orderService.getOrdersByCompanies();
    }

    @GetMapping("/statistic/byTags")
    public Map<String,Long> getAllByTags() {
        return orderService.getOrdersByTags();
    }
}
