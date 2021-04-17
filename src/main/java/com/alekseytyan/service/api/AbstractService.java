package com.alekseytyan.service.api;

import java.util.List;

public interface AbstractService<E, DTO> {
    E findById(Long id);

    List<E> findAll();

    void save(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(Long entityId);

    DTO convertToDTO(E entity);
}
