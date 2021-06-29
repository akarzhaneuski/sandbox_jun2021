package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDAO dao;

    @Override
    public Company getById(Long id) {
        return dao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Company> getAll() {
        return dao.findAll();
    }

    @Override
    public Company save(Company company) {
        return dao.save(company);
    }

    @Override
    public Company update(Company company) {
        return dao.save(company);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Company> getCompaniesByLocation(Long id) {
        return dao.findAllByCountryId(id);
    }
}
