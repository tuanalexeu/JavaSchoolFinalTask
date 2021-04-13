package com.alekseytyan.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractJpaDao<T> {

   private Class<T> clazz;

   @PersistenceContext
   protected EntityManager entityManager;

   public void setClazz(Class<T> clazzToSet) {
      this.clazz = clazzToSet;
   }

   public T findOne(Long id) {
      return entityManager.find(clazz, id);
   }

   @SuppressWarnings("unchecked")
   public List<T> findAll() {
      return entityManager
              .createQuery("from " + clazz.getName())
              .getResultList();
   }

   public void save(T entity){
      entityManager.persist(entity);
   }

   public void update(T entity){
      entityManager.merge(entity);
   }

   public void delete(T entity) {
      entityManager.remove(entity);
   }

   public void deleteById(Long entityId){
      T entity = findOne(entityId);
      delete(entity);
   }
}