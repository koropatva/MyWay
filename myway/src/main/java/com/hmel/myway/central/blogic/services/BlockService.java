package com.hmel.myway.central.blogic.services;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IBlockService;
import com.hmel.myway.central.models.Block;
import com.hmel.myway.dao.blogic.services.AbstractHibernateDAO;

@Service
public class BlockService extends AbstractHibernateDAO<Block, Long> implements
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

}