package com.hmel.myway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmel.myway.central.blogic.interfaces.IBlockService;
import com.hmel.myway.central.models.Block;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/block")
public class BlockController extends BaseController<Block, Long> {

	@Autowired
	private IBlockService iBlockService;

	@Override
	protected void setIHibernateDAO() {
		this.iHibernateDAO = iBlockService;
	}

}
