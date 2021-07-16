package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CompanyDto;
import com.exadel.sandbox.team5.util.CompanySearchCriteria;
import com.exadel.sandbox.team5.util.ResultPage;

import java.util.List;

public interface CompanyService extends CRUDService<CompanyDto> {

    List<CompanyDto> getCompaniesByLocation(Long id);

    ResultPage<CompanyDto> getByCriteria(CompanySearchCriteria criteria);
}
