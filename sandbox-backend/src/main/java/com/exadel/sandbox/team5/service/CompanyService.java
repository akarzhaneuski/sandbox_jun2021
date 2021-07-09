package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CompanyDto;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface CompanyService extends CRUDService<CompanyDto> {

    List<CompanyDto> getCompaniesByLocation(Long id);

    ByteArrayInputStream getStatisticFileOrdersByCompanies();
}
