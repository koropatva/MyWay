package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IPlaceService;
import com.hmel.myway.central.models.Place;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class PlaceService extends BaseHibernateDAO<Place, Long> implements IPlaceService {

}
