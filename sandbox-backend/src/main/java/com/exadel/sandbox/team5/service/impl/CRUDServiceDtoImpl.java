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
public class CRUDServiceDtoImpl<D extends IdentifierDto, S extends CommonRepository<E>, E extends BaseEntity> implements CRUDService<D> {

    protected final S entityDao;
    protected final E entity;
    protected final D entityDto;
    protected final MapperConverter mapper;

    @Override
    public D getById(Long id) {
        return (D) entityDao.findById(id)
                .map(entity -> mapper.map(entity, entityDto.getClass()))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<D> getAll() {
        return (List<D>) mapper.mapAll(entityDao.findAll(), entityDto.getClass());
    }

    @Override
    public D save(D entityDto) {
        E saveEntity = (E) mapper.map(entityDto, entity.getClass());
        return (D) mapper.map(entityDao.save(saveEntity), entityDto.getClass());
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
