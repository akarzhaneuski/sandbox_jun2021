package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{all}")
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

}
