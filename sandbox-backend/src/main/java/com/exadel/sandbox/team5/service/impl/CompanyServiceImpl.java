package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CompanyServiceImpl extends AbstractService<Company, CompanyDAO> implements CompanyService {

    public CompanyServiceImpl(CompanyDAO repository) {
        super(repository);
    }

    public List<Company> getCompaniesByLocation(Long id) {
        return repository.findAllByLocationsId(id);
    }
}
