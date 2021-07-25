package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.AddressDto;
import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.service.AddressService;
import com.exadel.sandbox.team5.service.LocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationService locationService;
    private final AddressService addressService;

    @GetMapping("/{id}")
    public CountryDto getLocation(@PathVariable Long id) {
        return locationService.getById(id);
    }

    @GetMapping
    public List<CountryDto> getAll() {
        return locationService.getAll();
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping
    public AddressDto save(@RequestBody AddressDto entity) {
        return addressService.save(entity);
    }

    @GetMapping("/all")
    public List<AddressDto> getAllAddress() {
        return addressService.getAll();
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("/{id}")
    public CountryDto update(@PathVariable Long id, @RequestBody CountryDto entity) {
        entity.setId(id);
        return locationService.update(entity);
    }
}
