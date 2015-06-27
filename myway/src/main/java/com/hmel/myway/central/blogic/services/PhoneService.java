package com.hmel.myway.central.blogic.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IPhoneService;
import com.hmel.myway.central.models.Phone;
import com.hmel.myway.exceptions.PhoneDictionaryException;

@Service
public class PhoneService implements IPhoneService {

  
  @Autowired
  @Qualifier("localSessionFactory")
  private SessionFactory sessionFactory;

  private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Phone saveUpdate(Phone phones) throws PhoneDictionaryException {
    logger.debug("saveUpdate phone");
    if (phones == null) {
      throw new IllegalArgumentException("Phones can't be NULL for saving");
    }
    getSession().beginTransaction();
    try {
      if (phones.getId() == 0) {
        getSession().persist(phones);
      } else {
        getSession().merge(phones);
      }
      getSession().getTransaction().commit();
    } finally {
      getSession().close();
    }
    return phones;
  }

  @Override
  public Phone findByID(int id) throws PhoneDictionaryException {
    if (id == 0) {
      throw new IllegalArgumentException("ID can't be 0");
    }
    Phone phones = null;
    getSession().beginTransaction();
    try {
      phones = (Phone) getSession().get(Phone.class, id);
    } finally {
      getSession().close();
    }
    if (phones == null) {
      throw new NullPointerException("No phones with Id: " + id);
    }
    return phones;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Phone> findAll() throws PhoneDictionaryException {
    String sql = "SELECT p FROM Phones p";
    getSession().beginTransaction();
    List<Phone> res = new ArrayList<Phone>();
    try {
      Query query = getSession().createQuery(sql);
      res = query.list();
    } finally {
      getSession().close();
    }
    return res;
  }

  @Override
  public List<Phone> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException {
    if (criteria == null) {
      throw new IllegalArgumentException("criteria can't be null");
    }
    if (firstResult < 0) {
      throw new IllegalArgumentException("firstResult can't be < 0");
    }

    if (maxResult < 0) {
      throw new IllegalArgumentException("maxResults can't be < 0");
    }
    getSession().beginTransaction();
    List<Phone> res = new ArrayList<Phone>();
    try {
      res =
          criteria.getExecutableCriteria(getSession()).setFirstResult(firstResult)
              .setMaxResults(maxResult).list();
    } finally {
      getSession().close();
    }
    return res;
  }

  @Override
  public void delete(int id) throws PhoneDictionaryException {
    if (id == 0) {
      throw new IllegalArgumentException("Id can't be 0");
    }

    try {
      Phone phones = findByID(id);
      if (phones != null) {
        getSession().beginTransaction();
        getSession().delete(phones);
        getSession().getTransaction().commit();
      }
    } finally {
      getSession().close();
    }
  }

  private Session getSession() throws PhoneDictionaryException {
    return sessionFactory.getCurrentSession();
  }

}
