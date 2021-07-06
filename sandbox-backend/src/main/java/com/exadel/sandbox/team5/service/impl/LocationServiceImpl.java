package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.entity.Country;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LocationServiceImpl extends CRUDServiceDtoImpl<CountryDto, LocationDAO, Country> implements LocationService {

    public LocationServiceImpl(LocationDAO locationDAO, Country country, CountryDto countryDto, MapperConverter mapper) {
        super(locationDAO, country, countryDto, mapper);
    }
}
