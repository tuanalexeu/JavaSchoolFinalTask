package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dao.api.AbstractDao;
import com.alekseytyan.logiweb.service.api.AbstractService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        return convertToDTO(dao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return convertToDTO(dao.findAll());
    }

    @Override
    @Transactional
    public DTO save(DTO dto) {
        return convertToDTO(dao.save(convertToEntity(dto)));
    }

    @Override
    @Transactional
    public DTO update(DTO dto) {
        return convertToDTO(dao.update(convertToEntity(dto)));
    }

    @Override
    @Transactional
    public DTO delete(DTO dto) {
        return convertToDTO(dao.delete(convertToEntity(dto)));
    }

    @Override
    @Transactional
    public DTO deleteById(ID entityId) {
        return convertToDTO(dao.deleteById(entityId));
    }

    @Override
    public DTO convertToDTO(E entity) {
        return getMapper().map(entity, dtoClass);
    }

    @Override
    public List<DTO> convertToDTO(List<E> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public E convertToEntity(DTO dto) {
        return getMapper().map(dto, entityClass);
    }

    @Override
    public List<E> convertToEntity(List<DTO> entities) {
        return entities.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
