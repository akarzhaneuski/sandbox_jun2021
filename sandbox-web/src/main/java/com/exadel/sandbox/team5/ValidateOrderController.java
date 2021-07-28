package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.OrderStatus;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.QRCodeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders/validate")
@RequiredArgsConstructor
public class ValidateOrderController {

    private final QRCodeService qrCodeService;
    private final OrderService orderService;
    private final DiscountService discountService;

    @ApiOperation("Checks link if unique code of employee exists in database and promocode has not expired")
    @GetMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView validateQRCode(@PathVariable String uuid) {
        var responsePair = qrCodeService.validateQR(uuid);
        if (responsePair.getFirst() == OrderStatus.VALID){
            orderService.invalidatePromoCode(uuid);
        }
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("validationQR");
        modelAndView.addObject("validationResult", responsePair.getFirst().getText());
        modelAndView.addObject("email", responsePair.getSecond());
        modelAndView.addObject("discountName", discountService.getDiscountNameByOrderUUID(uuid));
        modelAndView.addObject("causeOfError", responsePair.getSecond());
        return modelAndView;
    }
}
