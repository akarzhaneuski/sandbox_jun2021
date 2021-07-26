package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.annotations.Validate;
import com.exadel.sandbox.team5.dao.AddressDAO;
import com.exadel.sandbox.team5.dto.AddressDto;
import com.exadel.sandbox.team5.entity.Address;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AddressServiceImpl extends CRUDServiceDtoImpl<AddressDAO, Address, AddressDto> implements AddressService {

    public AddressServiceImpl(AddressDAO entityDao, MapperConverter mapper) {
        super(entityDao, Address.class, AddressDto.class, mapper);
    }

    @Override
    @Validate
    public AddressDto save(AddressDto entityDto) {
        entityDto.getCity().setId(entityDao.findIdCityByName(entityDto.getCity().getName()).orElse(null));
        entityDto.getCity().getCountry().setId(entityDao.findIdCountryByName(entityDto.getCity().getCountry().getName()).orElse(null));
        var address = mapper.map(entityDto, Address.class);
        return mapper.map(entityDao.saveOrUpdate(address), AddressDto.class);
    }


}

