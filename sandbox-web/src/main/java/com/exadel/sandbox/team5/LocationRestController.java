package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.LocationDto;
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
    public LocationDto getLocation(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public List<LocationDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public LocationDto save(@RequestBody LocationDto entity){
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public LocationDto update(@PathVariable Long id, @RequestBody LocationDto entity){
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
