package com.alekseytyan.dao.api;

import java.util.List;

public interface AbstractDao<E> {

    E findById(Long id);

    List<E> findAll();

    void save(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(Long entityId);

}
