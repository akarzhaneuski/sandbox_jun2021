package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.QRCodeService;
import com.exadel.sandbox.team5.util.CreateOrder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final QRCodeService qrCodeService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto order) {
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @PutMapping("/invalidate")
    public OrderDto invalidate(@RequestBody OrderDto order) {
        return orderService.invalidatePromoCode(order.getDiscount().getId(), order.getEmployeePromocode());
    }

    @PutMapping("/create")
    public OrderDto create(@RequestBody CreateOrder createOrder) {
        if (createOrder != null && createOrder.getAmountDiscountDays() == 0) {
            createOrder.setAmountDiscountDays(7);
        }
        return orderService.createOrder(createOrder);
    }

    @ApiOperation("Generating QR code")
    @GetMapping(value = "/validate/{uuid}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] generateQRCode(@PathVariable String uuid) {
        return qrCodeService.generateQRCode(uuid);
    }

    @ApiOperation("Checks link if uuid exists in database and not expired promocode valid")
    @GetMapping(value = "/validate/{uuid}/{expirationTime}")
    public String validateQRCode(@PathVariable String uuid, @PathVariable String expirationTime) {
        var date = new Date(Long.parseLong(expirationTime)).getTime();
        return date > System.currentTimeMillis() && qrCodeService.checkUUID(uuid)
                ? "Promocode is valid"
                : "Promocode is not valid :(";
    }
}
