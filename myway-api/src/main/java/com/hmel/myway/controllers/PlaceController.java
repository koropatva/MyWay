package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.IPlaceService;
import com.hmel.myway.central.models.Place;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/place")
public class PlaceController extends BaseController<Place, Long> {

	@Autowired
	private IPlaceService iPlaceService;

	protected void setIHibernateDAO() {
		this.iHibernateDAO = iPlaceService;
	}

}