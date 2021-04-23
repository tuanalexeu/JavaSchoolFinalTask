package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDaoImpl<E, ID> implements AbstractDao<E, ID> {

   private final Class<E> clazz;

   @PersistenceContext
   protected EntityManager entityManager;

   public AbstractDaoImpl(Class<E> clazz) {
      this.clazz = clazz;
   }

   @Override
   public E findById(ID id) {
      return entityManager.find(clazz, id);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<E> findAll() {
      return entityManager
              .createQuery("from " + clazz.getName())
              .getResultList();
   }

   @Override
   public void save(E entity){
      entityManager.persist(entity);
   }

   @Override
   public void update(E entity){
      entityManager.merge(entity);
   }

   @Override
   public void delete(E entity) {
      entityManager.remove(entity);
   }

   @Override
   public void deleteById(ID entityId){
      E entity = findById(entityId);
      delete(entity);
   }
}