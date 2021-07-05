package com.exadel.sandbox.team5.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperConverter {

    private final ModelMapper modelMapper;

    public <T, K> K map(T entity, Class<K> kClass) {
        return modelMapper.map(entity, kClass);
    }

    public <E, K> List<K> mapAll(List<E> entityList, Class<K> kClass) {
        return entityList.stream()
                .map(entity -> map(entity, kClass))
                .toList();
    }

    public <E, K> Page<K> mapToPage(Page<E> entityList, Class<K> kClass) {
        List<K> result = entityList.stream()
                .map(entity -> map(entity, kClass))
                .toList();
        return new PageImpl<>(result, PageRequest.of(entityList.getNumber(), entityList.getSize()), result.size());
    }
}
