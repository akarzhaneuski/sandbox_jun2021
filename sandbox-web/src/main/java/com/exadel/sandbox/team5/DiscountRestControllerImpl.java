package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.impl.DiscountServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
public class DiscountRestControllerImpl extends BaseControllerImpl<Discount, DiscountServiceImpl> {

    DiscountRestControllerImpl(DiscountServiceImpl service) {
        super(service);
    }
}