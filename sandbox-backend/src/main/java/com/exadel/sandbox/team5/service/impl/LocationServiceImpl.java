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
public class LocationServiceImpl extends CRUDServiceDtoImpl<LocationDAO, Country, CountryDto> implements LocationService {

    public LocationServiceImpl(LocationDAO locationDAO, MapperConverter mapper) {
        super(locationDAO, Country.class, CountryDto.class, mapper);
    }
}
