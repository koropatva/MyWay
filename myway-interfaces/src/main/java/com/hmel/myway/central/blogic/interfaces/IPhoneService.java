package com.hmel.myway.central.blogic.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.myway.central.models.Phone;
import com.hmel.myway.exceptions.PhoneDictionaryException;

public interface IPhoneService {

  public Phone saveUpdate(Phone phones) throws PhoneDictionaryException;

  public Phone findByID(int id) throws PhoneDictionaryException;

  public List<Phone> findAll() throws PhoneDictionaryException;

  public List<Phone> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult)
      throws PhoneDictionaryException;

  public void delete(int id) throws PhoneDictionaryException;

}
