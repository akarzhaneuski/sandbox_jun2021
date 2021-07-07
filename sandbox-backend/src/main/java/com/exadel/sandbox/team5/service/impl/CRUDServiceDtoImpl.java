package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CommonRepository;
import com.exadel.sandbox.team5.dto.IdentifierDto;
import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.CRUDService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class CRUDServiceDtoImpl<S extends CommonRepository<E>, E extends BaseEntity, D extends IdentifierDto> implements CRUDService<D> {

    protected final S entityDao;
    protected final Class<E> entityClass;
    protected final Class<D> entityDtoClass;
    protected final MapperConverter mapper;

    @Override
    public D getById(Long id) {
        return mapper.map(entityDao.findById(id).orElseThrow(NoSuchElementException::new), entityDtoClass);
    }

    @Override
    public List<D> getAll() {
        return mapper.mapAll(entityDao.findAll(), entityDtoClass);
    }

    @Override
    public D save(D entityDto) {
        var saveEntity = mapper.map(entityDto, entityClass);
        return mapper.map(entityDao.saveAndFlush(saveEntity), entityDtoClass);
    }

    @Override
    public D update(D entityDto) {
        return this.save(entityDto);
    }

    @Override
    public void delete(Long id) {
        entityDao.deleteById(id);
    }
}
