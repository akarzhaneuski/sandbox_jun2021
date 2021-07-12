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
public class CompanyServiceImpl extends CRUDServiceDtoImpl<CompanyDAO, Company, CompanyDto> implements CompanyService {
    public CompanyServiceImpl(CompanyDAO entityDao, MapperConverter mapper) {
        super(entityDao, Company.class, CompanyDto.class, mapper);
    }

    @Override
    public List<CompanyDto> getCompaniesByLocation(Long id) {
        return mapper.mapAll(entityDao.findAllByCountryId(id), CompanyDto.class);
    }

    public ResultPage<CompanyDto> getAllSort(SearchCriteria criteria) {
        Page<Company> companies = dao.findAll(criteria.getPageRequest());
        return mapper.mapToPage(companies, CompanyDto.class);
    }

    @Override
    public ResultPage<CompanyDto> getByCriteria(CompanySearchCriteria criteria) {
        if (criteria.isEmpty()) {
            return getAllSort(criteria);
        }
        Page<Company> result = dao.findByNameContaining(criteria.getSearchText(), criteria.getPageRequest());
        return mapper.mapToPage(result, CompanyDto.class);
    }
}
