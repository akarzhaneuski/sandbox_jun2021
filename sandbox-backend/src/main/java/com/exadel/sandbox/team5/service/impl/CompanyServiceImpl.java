package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends CRUDServiceImpl<Company> {

    public CompanyServiceImpl(CompanyDAO repository) {
        super(repository);
    }
}
