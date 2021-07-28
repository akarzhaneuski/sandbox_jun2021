package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dao.AddressDAO;
import com.exadel.sandbox.team5.dto.AddressDto;
import com.exadel.sandbox.team5.dto.CityDto;
import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.entity.Address;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressServiceImpl.class)
class AddressServiceImplTest {

    @Autowired
    private AddressService service;

    @MockBean
    private AddressDAO entityDao;

    @MockBean
    MapperConverter mapper;

    AddressDto address;

    @BeforeEach
    public void createAddress() {
        CountryDto country = new CountryDto();
        country.setName("Poland");
        CityDto city = new CityDto();
        city.setName("Helm");
        city.setCountry(country);
        address = new AddressDto();
        address.setAddress("Warszavska str");
        address.setCity(city);
    }

    @Test
    void saveAddressWhenAllValuesIsNew() {
        AddressDto expected = address;

        service.save(address);

        Mockito.verify(entityDao, Mockito.times(1)).saveOrUpdate(mapper.map(address, Address.class));
        Mockito.verify(entityDao, Mockito.times(1)).findIdCityByName(expected.getCity().getName());
        Mockito.verify(entityDao, Mockito.times(1)).findIdCountryByName(expected.getCity().getCountry().getName());
    }

    @Test
    void saveAddressSetIdCountryWhenCountryExists() {
        Long expected = 1l;
        Mockito.when(entityDao.findIdCountryByName(Matchers.anyString())).thenReturn(Optional.of(1l));

        service.save(address);
        Long result = address.getCity().getCountry().getId();

        assertEquals(expected, result);
    }

    @Test
    void saveAddressSetIdCityWhenCityExists() {
        Long expected = 1l;
        Mockito.when(entityDao.findIdCityByName(Matchers.anyString())).thenReturn(Optional.of(1l));

        service.save(address);
        Long result = address.getCity().getId();

        assertEquals(expected, result);
    }
}