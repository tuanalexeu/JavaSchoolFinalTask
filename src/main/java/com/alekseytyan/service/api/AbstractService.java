package com.alekseytyan.service.api;

import java.util.List;

public interface AbstractService<E, DTO, ID> {
    DTO findById(ID id);

    List<DTO> findAll();

    void save(DTO dto);

    void update(DTO dto);

    void delete(DTO dto);

    void deleteById(ID entityId);

    DTO convertToDTO(E entity);

    List<DTO> convertToDTO(List<E> entities);

    E convertToEntity(DTO dto);

    List<E> convertToEntity(List<DTO> dtoList);
}
