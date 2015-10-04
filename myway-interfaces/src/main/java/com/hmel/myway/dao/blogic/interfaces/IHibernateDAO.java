package com.hmel.myway.dao.blogic.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */
public interface IHibernateDAO<T extends Serializable, P extends Serializable> {

	public T findOne(P id) throws PhoneDictionaryException;

	public List<T> findAll(int firstResult,
			int maxResults) throws PhoneDictionaryException;

	public T create(T entity) throws PhoneDictionaryException;

	public T update(T entity) throws PhoneDictionaryException;

	public T save(T entity) throws PhoneDictionaryException;

	public void delete(T entity) throws PhoneDictionaryException;

	public void deleteById(P entityId) throws PhoneDictionaryException;

	/**
	 * 
	 * @param criteria
	 * @return all records
	 */
	public List<T> findByCriteria(DetachedCriteria criteria)
			throws PhoneDictionaryException;

	/**
	 * 
	 * @param criteria
	 * @return records starting from firstResult and amount = maxResuls (or less
	 *         if not present necessary amout in db)
	 */
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) throws PhoneDictionaryException;

}
