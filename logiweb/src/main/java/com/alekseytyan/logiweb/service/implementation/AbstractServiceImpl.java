package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.aspect.LogAnnotation;
import com.alekseytyan.logiweb.dao.api.AbstractDao;
import com.alekseytyan.logiweb.service.api.AbstractService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractServiceImpl<E, D extends AbstractDao<E, ID>, DTO, ID> implements AbstractService<E, DTO, ID> {

    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private D dao;

    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private ModelMapper mapper;


    private final Class<DTO> dtoClass;
    private final Class<E> entityClass;

    @Override
    @LogAnnotation
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        return convertToDTO(dao.findById(id));
    }

    @Override
    @LogAnnotation
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return convertToDTO(dao.findAll());
    }

    @Override
    @LogAnnotation
    @Transactional(readOnly = true)
    public List<DTO> findPage(int size, int page) {
        return convertToDTO(getDao().findPage(size, page));
    }

    @Override
    @LogAnnotation
    @Transactional
    public DTO save(DTO dto) {
        return convertToDTO(dao.save(convertToEntity(dto)));
    }

    @Override
    @LogAnnotation
    @Transactional
    public DTO update(DTO dto) {
        return convertToDTO(dao.update(convertToEntity(dto)));
    }

    @Override
    @LogAnnotation
    @Transactional
    public DTO delete(DTO dto) {
        return convertToDTO(dao.delete(convertToEntity(dto)));
    }

    @Override
    @LogAnnotation
    @Transactional
    public DTO deleteById(ID entityId) {
        return convertToDTO(dao.deleteById(entityId));
    }

    @Override
    @LogAnnotation
    public DTO convertToDTO(E entity) {
        return entity != null
                ? getMapper().map(entity, dtoClass)
                : null;
    }

    @Override
    @LogAnnotation
    public List<DTO> convertToDTO(List<E> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @LogAnnotation
    public E convertToEntity(DTO dto) {
        return getMapper().map(dto, entityClass);
    }

    @Override
    @LogAnnotation
    public List<E> convertToEntity(List<DTO> entities) {
        return entities.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
