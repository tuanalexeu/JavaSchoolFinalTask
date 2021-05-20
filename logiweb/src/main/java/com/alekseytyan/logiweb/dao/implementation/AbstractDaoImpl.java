package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDaoImpl<E, ID> implements AbstractDao<E, ID> {

   private final Class<E> clazz;

   @PersistenceContext
   protected EntityManager entityManager;

   protected AbstractDaoImpl(Class<E> clazz) {
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
   public List<E> findPage(Integer size, Integer page) {
      return criteriaPage(size, page);
   }

   @Override
   public E save(E entity) {
      entityManager.persist(entity);
      entityManager.flush();
      return entity;
   }

   @Override
   public E update(E entity){
      return entityManager.merge(entity);
   }

   @Override
   public E delete(E entity) {
      entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
      return entity;
   }

   @Override
   public E deleteById(ID entityId){
      E entity = findById(entityId);
      delete(entity);
      return entity;
   }

   /**
    * Create paginated queries using Criteria API
    * @param size - needed size of page
    * @param page - needed page number
    * @return - paginated list of entities
    */
   protected List<E> criteriaPage(Integer size, Integer page) {

      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

      CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
      countQuery.select(criteriaBuilder.count(countQuery.from(clazz)));

      Long count = entityManager.createQuery(countQuery).getSingleResult(); // Amount of rows

      CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
      Root<E> from = criteriaQuery.from(clazz);
      CriteriaQuery<E> select = criteriaQuery.select(from);

      TypedQuery<E> typedQuery = entityManager.createQuery(select);

//      typedQuery.setFirstResult(page == null ? 0 : page - 1);
//      typedQuery.setMaxResults(size == null ? 10 : size);

      return typedQuery.getResultList();
   }

   /**
    * Creates native paginated query
    * @param query - prepared query
    * @param size - size of list
    * @param page - number of page
    * @return - paginated result list
    */
   protected List<E> queryPage(Query query, Integer size, Integer page) {

//      query.setFirstResult(page == null ? 0 : (page - 1) * page);
//      query.setMaxResults(size == null ? 10 : size);


      return query.getResultList();
   }


}