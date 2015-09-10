package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IDestinationService;
import com.hmel.myway.central.models.Destination;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class DestinationService extends BaseHibernateDAO<Destination, Long>
		implements IDestinationService {
}
