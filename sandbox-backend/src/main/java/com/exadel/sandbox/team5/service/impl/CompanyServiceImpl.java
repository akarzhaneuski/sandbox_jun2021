package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CompanyServiceImpl extends CRUDServiceImpl <Company, CompanyDAO> implements CompanyService {


    public CompanyServiceImpl(CompanyDAO repository) {
        super(repository);
    }

    @Override
    public List<Company> getCompaniesByLocation(Long id) {
        return repository.findAllByCountryId(id);
    }
}
