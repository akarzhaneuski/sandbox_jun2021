package com.exadel.sandbox.team5;

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

    @ApiOperation("Checks link if unique code of employee exists in database and promocode has not expired")
    @GetMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView validateQRCode(@PathVariable String uuid) {
        var responsePair = qrCodeService.validateQR(uuid);
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("validationQR");
        var response = responsePair.getSecond();
        var responseArray = response.split("/");
        modelAndView.addObject("validationResult", responsePair.getFirst().getText());
        modelAndView.addObject("email", responseArray[0]);
        modelAndView.addObject("discountName", responseArray[1]);
        modelAndView.addObject("causeOfError", responseArray[0]);
        return modelAndView;
    }
}
