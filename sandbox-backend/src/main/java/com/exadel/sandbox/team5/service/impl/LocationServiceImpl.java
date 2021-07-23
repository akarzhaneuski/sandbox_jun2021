package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.AddressDAO;
import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.dto.locationDiscountDto.AddressDiscountDto;
import com.exadel.sandbox.team5.dto.locationDiscountDto.CityDiscountDto;
import com.exadel.sandbox.team5.dto.locationDiscountDto.CountryDiscountDto;
import com.exadel.sandbox.team5.entity.Address;
import com.exadel.sandbox.team5.entity.City;
import com.exadel.sandbox.team5.entity.Country;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class LocationServiceImpl extends CRUDServiceDtoImpl<LocationDAO, Country, CountryDto> implements LocationService {

    private final AddressDAO addressDAO;

    public LocationServiceImpl(LocationDAO locationDAO, MapperConverter mapper, AddressDAO addressDAO) {
        super(locationDAO, Country.class, CountryDto.class, mapper);
        this.addressDAO = addressDAO;
    }

    //fixme feature for UI
    @Override
    public CountryDiscountDto getAllLocation(Long id) {
        List<Address> addresses = addressDAO.findByCompanyId(id);
        List<City> cities = addresses.stream().map(Address::getCity).distinct().collect(Collectors.toList());

        List<CityDiscountDto> allCity = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            CityDiscountDto cityDiscountDto = new CityDiscountDto();
            cityDiscountDto.setId(cities.get(i).getId());
            cityDiscountDto.setName(cities.get(i).getName());
            for (int j = 0; j < addresses.size(); j++) {
                Address address = addresses.get(j);
                if (cityDiscountDto.getName().equals(address.getCity().getName())) {
                    AddressDiscountDto addressDiscountDto = new AddressDiscountDto();
                    addressDiscountDto.setId(address.getId());
                    addressDiscountDto.setAddress(address.getAddress());
                    cityDiscountDto.setAddresses(addressDiscountDto);
                }
            }
            allCity.add(cityDiscountDto);
        }

        Country country = cities.stream().map(cityDto -> cityDto.getCountry()).findFirst().get();
        CountryDiscountDto result = new CountryDiscountDto();
        result.setId(country.getId());
        result.setName(country.getName());
        result.setCities(allCity);
        return result;
    }
}
