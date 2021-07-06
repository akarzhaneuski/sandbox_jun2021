package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.service.LocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationService service;

    @GetMapping("/{id}")
    public CountryDto getLocation(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public List<CountryDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public CountryDto save(@RequestBody CountryDto entity){
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public CountryDto update(@PathVariable Long id, @RequestBody CountryDto entity){
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
