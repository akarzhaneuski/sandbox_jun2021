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
//@RequiredArgsConstructor
public class CompanyServiceImpl extends CRUDServiceDtoImpl<CompanyDto, CompanyDAO, Company> implements CompanyService {
    public CompanyServiceImpl(CompanyDAO entityDao, Company company, CompanyDto companyDto, MapperConverter mapper) {
        super(entityDao, company, companyDto, mapper);
    }

//    private final CompanyDAO dao;
//    private final MapperConverter mapper;

//    @Override
//    public CompanyDto getById(Long id) {
//        return dao.findById(id)
//                .map(company -> mapper.map(company, CompanyDto.class))
//                .orElseThrow(NoSuchElementException::new);
//    }
//
//    @Override
//    public List<CompanyDto> getAll() {
//        return mapper.mapAll(dao.findAll(), CompanyDto.class);
//    }
//
//    @Override
//    public CompanyDto save(CompanyDto companyDto) {
//        Company company = mapper.map(companyDto, Company.class);
//        return mapper.map(dao.save(company), CompanyDto.class);
//    }
//
//    @Override
//    public CompanyDto update(CompanyDto company) {
//        return this.save(company);
//    }
//
//    @Override
//    public void delete(Long id) {
//        dao.deleteById(id);
//    }

    @Override
    public List<CompanyDto> getCompaniesByLocation(Long id) {
        return mapper.mapAll(entityDao.findAllByCountryId(id), CompanyDto.class);
    }
}
