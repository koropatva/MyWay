package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class CriteriaService extends BaseHibernateDAO<Criteria, Long> implements
		ICriteriaService {

}
