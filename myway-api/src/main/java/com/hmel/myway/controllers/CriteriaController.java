package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Criteria;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/criteria")
public class CriteriaController extends BaseController<Criteria, Long> {

	@Autowired
	private ICriteriaService iCriteriaService;

	
	protected void setIHibernateDAO() {
		this.iHibernateDAO = iCriteriaService;
	}

}