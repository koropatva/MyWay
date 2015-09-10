package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.ICriteriaBlockService;
import com.hmel.myway.central.models.CriteriaBlock;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class CriteriaBlockService extends BaseHibernateDAO<CriteriaBlock, Long>
		implements ICriteriaBlockService {

}
