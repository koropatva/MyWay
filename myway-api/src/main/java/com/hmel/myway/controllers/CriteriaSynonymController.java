package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.ICriteriaSynonymService;
import com.hmel.myway.central.models.CriteriaSynonym;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/criteria_synonym")
public class CriteriaSynonymController extends BaseController<CriteriaSynonym, Long> {

	@Autowired
	private ICriteriaSynonymService iCriteriaSynonymService;

	@Override
	protected void setIHibernateDAO() {
		this.iHibernateDAO = iCriteriaSynonymService;
	}

}
