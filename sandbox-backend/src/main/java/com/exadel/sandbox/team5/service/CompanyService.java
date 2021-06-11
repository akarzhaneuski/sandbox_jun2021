package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CompanyService extends CRUDService<Company> {

    Page<Company> findAll(Pageable pageable);
    Page<Company> findAllByLocationsId(Long id, Pageable pageable);
}
