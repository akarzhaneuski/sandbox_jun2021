package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dto.CompanyDto;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.CompanyService;
import com.exadel.sandbox.team5.service.convertor.CSVConvertor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
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

    @Override
    public ByteArrayInputStream getStatisticCSVFileOrdersByCompanies() {
        ByteArrayInputStream in = new CSVConvertor().createFile(orderService.getOrdersByCompanies(), "Companies", "Orders");
        return in;
    }
}
