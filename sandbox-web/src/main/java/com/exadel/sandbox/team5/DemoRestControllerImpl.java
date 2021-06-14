package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.DemoEntity;
import com.exadel.sandbox.team5.service.impl.DemoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoRestControllerImpl extends BaseControllerImpl<DemoEntity, DemoServiceImpl> {

    DemoRestControllerImpl(DemoServiceImpl service) {
        super(service);
    }
}