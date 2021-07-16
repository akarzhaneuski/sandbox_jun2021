package com.exadel.sandbox.team5.mapper;

import com.exadel.sandbox.team5.util.ResultPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public <E, K> ResultPage<K> mapToPage(Page<E> entityList, Class<K> kClass) {
        var res = entityList.map(entity -> map(entity, kClass));
        return new ResultPage<>(res.getContent(), entityList.getTotalElements());
    }
}
