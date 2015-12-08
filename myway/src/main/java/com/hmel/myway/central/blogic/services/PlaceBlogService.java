package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IPlaceBlogService;
import com.hmel.myway.central.models.PlaceBlog;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class PlaceBlogService extends BaseHibernateDAO<PlaceBlog, Long> implements IPlaceBlogService {

}
