package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.AbstractDao;
import com.alekseytyan.service.api.AbstractService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractServiceImpl<E, D extends AbstractDao<E>, DTO> implements AbstractService<E, DTO> {

    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private D dao;

    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private ModelMapper mapper;


    private final Class<DTO> dtoClass;

    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void save(E entity) {
        dao.save(entity);
    }

    @Override
    @Transactional
    public void update(E entity) {
        dao.update(entity);
    }

    @Override
    @Transactional
    public void delete(E entity) {
        dao.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long entityId) {
        dao.deleteById(entityId);
    }

    @Override
    public DTO convertToDTO(E entity) {
        return getMapper().map(entity, dtoClass);
    }

    @Override
    public List<DTO> convertToDTO(List<E> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
