package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.AbstractDao;
import com.alekseytyan.service.api.AbstractService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public abstract class AbstractServiceImpl<E, D extends AbstractDao<E>, DTO> implements AbstractService<E, DTO> {

    private D dao;
    private ModelMapper mapper;

    @Override
    public E findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<E> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(E entity) {
        dao.save(entity);
    }

    @Override
    public void update(E entity) {
        dao.update(entity);
    }

    @Override
    public void delete(E entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        dao.deleteById(entityId);
    }


}
