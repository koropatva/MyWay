package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.ICriteriaPlaceService;
import com.hmel.myway.central.models.CriteriaPlace;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class CriteriaPlaceService extends BaseHibernateDAO<CriteriaPlace, Long> implements ICriteriaPlaceService {

}
