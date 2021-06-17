package com.exadel.sandbox.team5.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MapperConverter {

    private final ModelMapper modelMapper;

    public <T, K> K map(T discount, Class<K> kClass) {
        return modelMapper.map(discount, kClass);
    }

    public <E, K> List<K> convertList(List<E> list) {
        return modelMapper.map(list, new TypeToken<List<K>>() {}.getType());
    }
}
