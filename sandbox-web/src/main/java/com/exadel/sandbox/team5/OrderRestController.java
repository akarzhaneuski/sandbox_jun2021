package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.QRCodeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto order) {
        order.setId(id);
        return orderService.update(order);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }


    @ApiOperation("Create order from register user and return QR code with link")
    @PostMapping(value = "/create/{discountId}", produces = MediaType.IMAGE_PNG_VALUE)
    public String create(@PathVariable String discountId) {
        var encodedQR = "data:image/png;base64," +
                StringUtils.newStringUtf8(Base64.encodeBase64(qrCodeService.generateQRCode(orderService.createOrder(discountId)), false));
        return String.format("<img src={`%s`}  />", encodedQR);
    }

    @ApiOperation("Checks link if unique code of employee exists in database and promocode has not expired")
    @GetMapping(value = "/validate/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public String validateQRCode(@PathVariable String uuid) {
        return qrCodeService.validateQR(uuid);
    }
}
