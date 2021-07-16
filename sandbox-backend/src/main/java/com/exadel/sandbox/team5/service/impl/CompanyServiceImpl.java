package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dto.CompanyDto;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.CompanyService;
import com.exadel.sandbox.team5.util.CompanySearchCriteria;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CompanyServiceImpl extends CRUDServiceDtoImpl<CompanyDAO, Company, CompanyDto> implements CompanyService {

    private final OrderServiceImpl orderService;

    public CompanyServiceImpl(CompanyDAO entityDao, MapperConverter mapper, OrderServiceImpl orderService) {
        super(entityDao, Company.class, CompanyDto.class, mapper);
        this.orderService = orderService;
    }

    @Override
    public List<CompanyDto> getCompaniesByLocation(Long id) {
        return mapper.mapAll(entityDao.findAllByCountryId(id), CompanyDto.class);
    }

    public ResultPage<CompanyDto> getAllSort(SearchCriteria criteria) {
        Page<Company> companies = entityDao.findAll(criteria.getPageRequest());
        return mapper.mapToPage(companies, CompanyDto.class);
    }

    @Override
    public ResultPage<CompanyDto> getByCriteria(CompanySearchCriteria criteria) {
        if (criteria.isEmpty()) {
            return getAllSort(criteria);
        }
        Page<Company> result = entityDao.findByNameContaining(criteria.getSearchText(), criteria.getPageRequest());
        return mapper.mapToPage(result, CompanyDto.class);
    }

    @Override
    public CompanyDto update(CompanyDto companyDto){
        return this.save(companyDto);
    }

    @Override
    public CompanyDto save(CompanyDto companyDto){
        var company = mapper.map(companyDto, Company.class);
        return mapper.map(entityDao.save(company), CompanyDto.class);
    }
}
