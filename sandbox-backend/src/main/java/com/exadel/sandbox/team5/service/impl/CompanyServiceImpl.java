package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends CRUDServiceImpl<Company, CompanyDAO> {

    public CompanyServiceImpl(CompanyDAO repository) {
        super(repository);
    }

    public List<Company> getCompaniesByLocation(Long id) {
        return repository.findAllByLocationsId(id);
    }
}
