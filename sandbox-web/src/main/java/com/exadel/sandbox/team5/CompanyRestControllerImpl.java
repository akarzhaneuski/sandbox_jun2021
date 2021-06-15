package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.impl.CompanyServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyRestControllerImpl extends BaseControllerImpl<Company, CompanyServiceImpl> {

    CompanyRestControllerImpl(CompanyServiceImpl service) {
        super(service);
    }
}