package com.srisris.jbc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.srisris.jbc.dao.DomainObject;
import com.srisris.jbc.dao.GenericDao;


public class GenericDaoImpl<T extends DomainObject> implements GenericDao<T> {
  
  private Class<T> type;
  protected EntityManager entityManager;

  /**
   * @param type
   */
  public GenericDaoImpl(Class<T> type) {
    super();
    this.type = type;
  }

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional(readOnly = true)
  public T get(Long id) {
    if (id == null) {
      return null;
    } else {
      return entityManager.find(type, id);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public List<T> getAll() {
    return entityManager.createQuery("select o from " + type.getName() + " o").getResultList();
  }

  @Override
  @Transactional
  public void save(T object) {
    entityManager.persist(object);

  }

  @Override
  public void delete(T object) {
    entityManager.remove(object);
  }


}
