package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.ICriteriaSynonymService;
import com.hmel.myway.central.models.CriteriaSynonym;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class CriteriaSynonymService extends BaseHibernateDAO<CriteriaSynonym, Long> implements ICriteriaSynonymService {

}
