package com.alekseytyan.dao.api;

import java.util.List;

public interface AbstractDao<E, ID> {

    E findById(ID id);

    List<E> findAll();

    void save(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(ID entityId);

}
