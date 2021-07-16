package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.ImageDAO;
import com.exadel.sandbox.team5.dto.CompanyDto;
import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.CompanyService;
import com.exadel.sandbox.team5.util.CompanySearchCriteria;
import com.exadel.sandbox.team5.util.Pair;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
public class CompanyServiceImpl extends CRUDServiceDtoImpl<CompanyDAO, Company, CompanyDto> implements CompanyService {

    private final ImageDAO imageDAO;

    public CompanyServiceImpl(CompanyDAO entityDao, MapperConverter mapper, ImageDAO imageDAO) {
        super(entityDao, Company.class, CompanyDto.class, mapper);
        this.imageDAO = imageDAO;
    }

    @Override
    public CompanyDto getById(Long id) {
        Company company = entityDao.findById(id).orElseThrow(NoSuchElementException::new);
        CompanyDto companyDto = mapper.map(company, CompanyDto.class);
        if (company.getImageId() != null) {
            companyDto.setNameImage(imageDAO.getById(company.getImageId()).getName());
        }
        return companyDto;
    }

    @Override
    public CompanyDto save(CompanyDto entityDto) {
        Company company = mapper.map(entityDto, Company.class);
        company.setImageId(imageDAO.findImageByName(entityDto.getNameImage()).orElseThrow(NoSuchElementException::new).getId());
        return mapper.map(entityDao.saveAndFlush(company), CompanyDto.class);
    }

    @Override
    public CompanyDto update(CompanyDto entityDto) {
        return this.save(entityDto);
    }

    @Override
    public List<CompanyDto> getAll() {
        return findAll(new SearchCriteria()).getContent();
    }

    @Override
    public List<CompanyDto> getCompaniesByLocation(Long id) {
        return mapper.mapAll(entityDao.findAllByCountryId(id), CompanyDto.class);
    }

    public ResultPage<CompanyDto> findAll(SearchCriteria criteria) {
        Page<Company> companies = entityDao.findAll(criteria.getPageRequest());
        var result = mapper.mapToPage(companies, CompanyDto.class);
        setNameImage(companies, result);
        return result;
    }

    @Override
    public ResultPage<CompanyDto> getByCriteria(CompanySearchCriteria criteria) {
        if (criteria.isEmpty()) {
            return findAll(criteria);
        }
        Page<Company> companies = entityDao.findByNameContaining(criteria.getSearchText(), criteria.getPageRequest());
        var result = mapper.mapToPage(companies, CompanyDto.class);
        setNameImage(companies, result);
        return result;
    }

    private void setNameImage(Page<Company> companies, ResultPage<CompanyDto> result) {
        List<Long> imageId = companies.getContent().stream().map(Company::getImageId).collect(Collectors.toList());
        Map<Long, String> namesImages = imageDAO.getAllName(imageId).stream()
                .collect(Collectors.toMap(x -> Long.parseLong(x.getFirst()), Pair::getSecond));
        Map<Long, String> nameImageToDto = new HashMap<>();
        companies.forEach(d -> nameImageToDto.put(d.getId(), namesImages.get(d.getImageId())));
        result.getContent().forEach(discountDto -> discountDto.setNameImage(nameImageToDto.get(discountDto.getId())));
    }
}
