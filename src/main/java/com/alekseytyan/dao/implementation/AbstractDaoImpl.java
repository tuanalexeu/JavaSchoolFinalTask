package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDaoImpl<E> implements AbstractDao<E> {

   private Class<E> clazz;

   @PersistenceContext
   protected EntityManager entityManager;

   public void setClazz(Class<E> clazzToSet) {
      this.clazz = clazzToSet;
   }

   @Override
   public E findById(Long id) {
      return entityManager.find(clazz, id);
   }

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
   public void deleteById(Long entityId){
      E entity = findById(entityId);
      delete(entity);
   }
}