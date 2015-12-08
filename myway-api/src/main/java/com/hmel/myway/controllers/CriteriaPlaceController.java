package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.ICriteriaPlaceService;
import com.hmel.myway.central.models.CriteriaPlace;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/criteria_place")
public class CriteriaPlaceController extends BaseController<CriteriaPlace, Long> {

	@Autowired
	private ICriteriaPlaceService iCriteriaPlaceService;

	@Override
	protected void setIHibernateDAO() {
		this.iHibernateDAO = iCriteriaPlaceService;
	}

}
