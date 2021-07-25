package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.QRCodeService;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResultPage<DiscountDto> getAll(@RequestBody SearchCriteria searchCriteria) {
        return orderService.getAll(searchCriteria);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto order) {
        order.setId(id);
        return orderService.update(order);
    }

    @ApiOperation("Create order from register user and return QR code with link")
    @PostMapping(value = "/create/{discountId}", produces = MediaType.IMAGE_PNG_VALUE)
    public String create(@PathVariable String discountId) {
        return "data:image/png;base64," +
                StringUtils.newStringUtf8(Base64.encodeBase64(
                        qrCodeService.generateQRCode(orderService.createOrder(discountId)), false));
    }
}
