package com.alekseytyan.logiweb.dao.api;

import java.util.List;

public interface AbstractDao<E, ID> {

    E findById(ID id);

    List<E> findAll();

    List<E> findPage(int size, int page);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    E deleteById(ID entityId);

}
