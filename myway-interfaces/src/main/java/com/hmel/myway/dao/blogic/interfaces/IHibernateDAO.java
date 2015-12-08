package com.hmel.myway.dao.blogic.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hmel.myway.exceptions.MyWayException;

/**
 * @author Burkovskiy Alexander
 */
public interface IHibernateDAO<T extends IEntity, P extends Serializable> {

	public T findOne(P id) throws MyWayException;

	public List<T> findAll(int firstResult,
			int maxResults) throws MyWayException;

	public T create(T entity) throws MyWayException;

	public T update(T entity) throws MyWayException;

	public T save(T entity) throws MyWayException;

	public void delete(T entity) throws MyWayException;

	public void deleteById(P entityId) throws MyWayException;

	/**
	 * 
	 * @param criteria
	 * @return all records
	 */
	public List<T> findByCriteria(DetachedCriteria criteria)
			throws MyWayException;

	/**
	 * 
	 * @param criteria
	 * @return records starting from firstResult and amount = maxResuls (or less
	 *         if not present necessary amout in db)
	 */
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
 int maxResults) throws MyWayException;

}
