package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.AbstractDao;
import com.alekseytyan.service.api.AbstractService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public abstract class AbstractServiceImpl<E, D extends AbstractDao<E>, DTO> implements AbstractService<E, DTO> {

    private D dao;
    private ModelMapper mapper;

    @Override
    @Transactional
    public E findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public List<E> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void save(E entity) {
        dao.save(entity);
    }

    @Override
    @Transactional
    public void update(E entity) {
        dao.update(entity);
    }

    @Override
    @Transactional
    public void delete(E entity) {
        dao.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long entityId) {
        dao.deleteById(entityId);
    }

}
