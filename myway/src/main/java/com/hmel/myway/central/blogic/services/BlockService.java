package com.hmel.myway.central.blogic.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IBlockService;
import com.hmel.myway.central.holder.BaseConditionHolder;
import com.hmel.myway.central.models.Block;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;
import com.hmel.myway.exceptions.PhoneDictionaryException;

@Service
public class BlockService extends BaseHibernateDAO<Block, Long> implements
		IBlockService {

	@Autowired
	@Qualifier("localSessionFactory")
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(BlockService.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Block> findByConditionHolder(BaseConditionHolder holder) throws PhoneDictionaryException {
		logger.debug("findByConditionHolder");
		if(holder==null){
			throw new IllegalArgumentException("Holder is null");
		}
		DetachedCriteria criteria = holder.loadCriteria();
		int firstResult = holder.firstPage;
		int maxResults = holder.pageSize;
		if (criteria == null) {
			throw new IllegalArgumentException("criteria can't be null");
		}
		if (firstResult < 0) {
			throw new IllegalArgumentException("firstResult can't be < 0");
		}

		if (maxResults < 0) {
			throw new IllegalArgumentException("maxResults can't be < 0");
		}
		List<Block> res = (List<Block>) criteria.getExecutableCriteria(getCurrentSession()).setFirstResult(firstResult)
				.setMaxResults(maxResults).list();
		return res;
	}

}
