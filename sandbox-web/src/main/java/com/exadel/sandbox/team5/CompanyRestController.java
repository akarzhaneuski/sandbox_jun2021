package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.impl.CompanyServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRestController extends AbstractController<Company, CompanyServiceImpl> {

    CompanyRestController(CompanyServiceImpl service) {
        super(service);
    }

    @GetMapping("/{locationId}/companies")
    public List<Company> getCompaniesByLocation(@PathVariable Long locationId) {
        return service.getListEntityByOtherEntityId(locationId);
    }
}