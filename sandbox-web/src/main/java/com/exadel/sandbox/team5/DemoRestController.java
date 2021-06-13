package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.DemoEntity;
import com.exadel.sandbox.team5.service.impl.DemoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoRestController extends AbstractController<DemoEntity, DemoServiceImpl> {

    DemoRestController(DemoServiceImpl service) {
        super(service);
    }
}