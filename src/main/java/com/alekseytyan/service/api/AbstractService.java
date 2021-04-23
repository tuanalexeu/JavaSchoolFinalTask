package com.alekseytyan.service.api;

import java.util.List;

public interface AbstractService<E, DTO, ID> {
    E findById(ID id);

    List<E> findAll();

    void save(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(ID entityId);

    DTO convertToDTO(E entity);

    List<DTO> convertToDTO(List<E> entities);
}
