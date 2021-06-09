package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Company;

import java.util.List;

public interface CompanyService extends CRUDService<Company> {

    List<Company> getCompaniesByLocation(Long id);
}
