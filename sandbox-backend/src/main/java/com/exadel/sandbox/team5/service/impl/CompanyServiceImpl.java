package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dto.CompanyDto;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CompanyServiceImpl extends CRUDServiceDtoImpl<CompanyDto, CompanyDAO, Company> implements CompanyService {
    public CompanyServiceImpl(CompanyDAO entityDao, Company company, CompanyDto companyDto, MapperConverter mapper) {
        super(entityDao, company, companyDto, mapper);
    }

    @Override
    public List<CompanyDto> getCompaniesByLocation(Long id) {
        return mapper.mapAll(entityDao.findAllByCountryId(id), CompanyDto.class);
    }
}
