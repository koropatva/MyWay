package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IHotelService;
import com.hmel.myway.central.models.Hotel;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class HotelService extends BaseHibernateDAO<Hotel, Long> implements IHotelService {

}
