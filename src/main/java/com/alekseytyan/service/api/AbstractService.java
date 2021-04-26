package com.alekseytyan.service.api;

import java.util.List;

public interface AbstractService<E, DTO, ID> {
    DTO findById(ID id);

    List<DTO> findAll();

    DTO save(DTO dto);

    DTO update(DTO dto);

    DTO delete(DTO dto);

    DTO deleteById(ID entityId);

    DTO convertToDTO(E entity);

    List<DTO> convertToDTO(List<E> entities);

    E convertToEntity(DTO dto);

    List<E> convertToEntity(List<DTO> dtoList);
}
