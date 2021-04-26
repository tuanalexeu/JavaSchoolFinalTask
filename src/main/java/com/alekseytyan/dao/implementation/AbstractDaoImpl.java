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

   @Override
   public List<E> findAll() {
      return entityManager
              .createQuery("from " + clazz.getName(), clazz)
              .getResultList();
   }

   @Override
   public E save(E entity) {
      entityManager.persist(entity);
      entityManager.flush();
      return entity;
   }

   @Override
   public E update(E entity){
      entityManager.merge(entity);
      return entity;
   }

   @Override
   public E delete(E entity) {
      entityManager.remove(entity);
      return entity;
   }

   @Override
   public E deleteById(ID entityId){
      E entity = findById(entityId);
      delete(entity);
      return entity;
   }
}