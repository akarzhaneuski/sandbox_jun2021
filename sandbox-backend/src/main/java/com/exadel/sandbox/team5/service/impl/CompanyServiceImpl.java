package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.entity.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CompanyServiceImpl extends AbstractService<Company, CompanyDAO> {

    public CompanyServiceImpl(CompanyDAO repository) {
        super(repository);
    }

    @Override
    public List<Company> getListEntityByOtherEntityId(Long id) {
        return repository.findAllByLocationsId(id);
    }
}
