package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.aspect.CrudAnnotation;

import java.util.List;

/**
 * Abstract service class is used to provide basic methods that are the same for all entities
 * @param <E> - Entity type
 * @param <DTO> - Entity DTO type
 * @param <ID> - Entity primary key type
 */
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

    /**
     * Converts given entity to its equivalent DTO class
     */
    DTO convertToDTO(E entity);

    /**
     * Converts list of entities to list of DTOs
     */
    List<DTO> convertToDTO(List<E> entities);

    /**
     * Converts given DTO class to its Entity equivalent
     */
    E convertToEntity(DTO dto);

    /**
     * Converts list of DTOs to list of entities
     */
    List<E> convertToEntity(List<DTO> dtoList);
}
