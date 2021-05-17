package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.aspect.CrudAnnotation;

import java.util.List;

public interface AbstractService<E, DTO, ID> {
    DTO findById(ID id);

    List<DTO> findAll();

    List<DTO> findPage(Integer size, Integer page);

    @CrudAnnotation(code = "update")
    DTO save(DTO dto);

    @CrudAnnotation(code = "update")
    DTO update(DTO dto);

    @CrudAnnotation(code = "update")
    DTO delete(DTO dto);

    @CrudAnnotation(code = "update")
    DTO deleteById(ID entityId);

    DTO convertToDTO(E entity);

    List<DTO> convertToDTO(List<E> entities);

    E convertToEntity(DTO dto);

    List<E> convertToEntity(List<DTO> dtoList);
}
