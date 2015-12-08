package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.IPlaceBlogService;
import com.hmel.myway.central.models.PlaceBlog;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/place_blog")
public class PlaceBlogController extends BaseController<PlaceBlog, Long> {

	@Autowired
	private IPlaceBlogService iPlaceBlogService;

	protected void setIHibernateDAO() {
		this.iHibernateDAO = iPlaceBlogService;
	}

}