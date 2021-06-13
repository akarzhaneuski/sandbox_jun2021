package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.impl.DiscountServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
public class DiscountRestController extends AbstractController<Discount, DiscountServiceImpl> {

    DiscountRestController(DiscountServiceImpl service) {
        super(service);
    }
}