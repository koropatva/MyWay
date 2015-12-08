package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.IHotelService;
import com.hmel.myway.central.models.Hotel;

/**
 * @author Burkovskiy Alexander
 */
@Controller
@RequestMapping(value = "/hotel")
public class HotelController extends BaseController<Hotel, Long> {

	@Autowired
	private IHotelService iHotelService;

	protected void setIHibernateDAO() {
		this.iHibernateDAO = iHotelService;
	}

}