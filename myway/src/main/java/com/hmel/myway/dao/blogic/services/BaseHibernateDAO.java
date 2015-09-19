package com.hmel.myway.dao.blogic.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hmel.myway.central.blogic.services.BlockService;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */
public abstract class BaseHibernateDAO<T extends Serializable, P extends Serializable> implements IHibernateDAO<T, P> {

	protected static final Logger logger = LoggerFactory.getLogger(BlockService.class);

	protected Class<T> clazz;

	@Autowired
	@Qualifier("localSessionFactory")
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseHibernateDAO() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T findOne(P id) throws PhoneDictionaryException {
		logger.info("findOne CALLING");
		getCurrentSession().beginTransaction();
		try {
			return (T) getCurrentSession().get(clazz, id);
		} finally {
			getCurrentSession().close();
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional(value = "transactionManager")
	public List<T> findAll(int firstResult, int maxResults) throws PhoneDictionaryException, IllegalArgumentException{
		logger.info("findAll CALLING");
		getCurrentSession().beginTransaction();
		if(firstResult<0){
			throw new IllegalArgumentException("first result must be >=0");
		}
		if(maxResults<=0 || maxResults<firstResult){
			throw new IllegalArgumentException("max Results must be >=0 and < first Result");
		}
		try {
			return getCurrentSession().createQuery("from " + clazz.getName()).setFirstResult(firstResult)
					.setMaxResults(maxResults).list();
		} finally {
			getCurrentSession().close();
		}
	}

	public T create(T entity) throws PhoneDictionaryException {
		logger.info("create CALLING");
		getCurrentSession().beginTransaction();
		try {
			getCurrentSession().persist(entity);
			getCurrentSession().getTransaction().commit();
		}

		finally {
			getCurrentSession().close();
		}
		return entity;
	}

	public T update(T entity) throws PhoneDictionaryException {
		logger.info("update CALLING");
		getCurrentSession().beginTransaction();
		try {
			getCurrentSession().merge(entity);
			getCurrentSession().getTransaction().commit();
		}

		finally {
			getCurrentSession().close();
		}
		return entity;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public T save(T entity) throws PhoneDictionaryException {
		logger.info("save CALLING");
		getCurrentSession().beginTransaction();
		try {
			getCurrentSession().saveOrUpdate(entity);
			getCurrentSession().getTransaction().commit();
		} finally {
			getCurrentSession().close();
		}
		return entity;
	}

	public void delete(T entity) throws PhoneDictionaryException {
		logger.info("delete CALLING");
		getCurrentSession().beginTransaction();
		try {
			getCurrentSession().delete(entity);
			getCurrentSession().getTransaction().commit();
		} finally {
			getCurrentSession().close();
		}
	}

	public void deleteById(P entityId) throws PhoneDictionaryException {
		logger.info("deleteById CALLING");
		T entity = findOne(entityId);
		delete(entity);

	}

	/**
	 * 
	 * @param criteria
	 * @return all records
	 */
	public List<T> findByCriteria(DetachedCriteria criteria) throws PhoneDictionaryException {
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
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults)
			throws PhoneDictionaryException {
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
		Transaction tx = getCurrentSession().beginTransaction();
		List<T> res = (List<T>) criteria.getExecutableCriteria(getCurrentSession()).setFirstResult(firstResult)
				.setMaxResults(maxResults).list();
		tx.commit();
		return res;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
