package com.hmel.myway.dao.blogic.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hmel.myway.central.blogic.services.BlockService;
import com.hmel.myway.dao.blogic.interfaces.IEntity;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

/**
 * @author Burkovskiy Alexander
 */
public abstract class BaseHibernateDAO<T extends IEntity, P extends Serializable>
		implements IHibernateDAO<T, P> {

	protected static final Logger logger = LoggerFactory
			.getLogger(BlockService.class);

	protected Class<T> clazz;

	@Autowired
	@Qualifier("localSessionFactory")
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseHibernateDAO() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public T findOne(P id) throws MyWayException {
		logger.info("findOne CALLING");
		try {
			getCurrentSession().beginTransaction();
			return (T) getCurrentSession().get(clazz, id);
		} finally {
			getCurrentSession().close();
		}

	}

	/*
	 * int firstResult, int maxResults
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(int firstResult, int maxResults)
			throws MyWayException, IllegalArgumentException {
		logger.info("findAll CALLING");

		if (firstResult < 0) {
			throw new IllegalArgumentException("first result must be >=0");
		}

		if (maxResults < 0) {
			throw new IllegalArgumentException("max Results must be >= 0");
		}

		try {
			getCurrentSession().beginTransaction();

			return getCurrentSession().createQuery("from " + clazz.getName())
					.setFirstResult(firstResult).setMaxResults(maxResults)
					.list();
		} finally {
			getCurrentSession().close();
		}
	}

	public T create(T entity) throws MyWayException {
		logger.info("create CALLING");

		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().persist(entity);
			getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			getCurrentSession().close();
		}
		return entity;
	}

	public T update(T entity) throws MyWayException {
		logger.info("update CALLING");

		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().merge(entity);
			getCurrentSession().getTransaction().commit();
		}

		finally {
			getCurrentSession().close();
		}
		return entity;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public T save(T entity) throws MyWayException {
		logger.info("save CALLING");

		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().saveOrUpdate(entity);
			getCurrentSession().getTransaction().commit();
		} finally {
			getCurrentSession().close();
		}
		return entity;
	}

	public void delete(T entity) throws MyWayException {
		logger.info("delete CALLING");

		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().delete(entity);
			getCurrentSession().getTransaction().commit();
		} finally {
			getCurrentSession().close();
		}
	}

	public void deleteById(P entityId) throws MyWayException {
		logger.info("deleteById CALLING");
		T entity = findOne(entityId);
		delete(entity);
	}

	/**
	 * 
	 * @param criteria
	 * @return all records
	 */
	public List<T> findByCriteria(DetachedCriteria criteria)
			throws MyWayException {
		logger.info("findByCriteria CALLING");

		return findByCriteria(criteria, 0, Integer.MAX_VALUE);
	}

	/**
	 * 
	 * @param criteria
	 * @return records starting from firstResult and amount = maxResuls (or less
	 *         if not present necessary amout in db)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) throws MyWayException {
		logger.info("findByCriteria CALLING");

		if (criteria == null) {
			throw new IllegalArgumentException("criteria can't be null");
		}

		if (firstResult < 0) {
			throw new IllegalArgumentException("firstResult can't be < 0");
		}

		if (maxResults < 0) {
			throw new IllegalArgumentException("maxResults can't be < 0");
		}

		try {
			getCurrentSession().beginTransaction();
			List<T> res = (List<T>) criteria
					.getExecutableCriteria(getCurrentSession())
					.setFirstResult(firstResult).setMaxResults(maxResults)
					.list();
			getCurrentSession().getTransaction().commit();

			return res;
		} finally {
			getCurrentSession().close();
		}
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
