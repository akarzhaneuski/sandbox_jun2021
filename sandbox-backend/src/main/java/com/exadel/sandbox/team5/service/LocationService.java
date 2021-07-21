package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.dto.locationDiscountDto.CountryDiscountDto;

public interface LocationService extends CRUDService<CountryDto> {

    CountryDiscountDto getAllLocation(Long id);
}
