package com.hmel.myway.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */

@RestController
public class HelloController {

	@Autowired
	private ICriteriaService iCriteriaService;

	private static final Logger logger = LoggerFactory
			.getLogger(HelloController.class);

	@RequestMapping
	public String index() throws PhoneDictionaryException {
		try {
			Criteria criteria = new Criteria();
			criteria.setDescription("Bla bla");
			criteria = iCriteriaService.save(criteria);
			return iCriteriaService.findOne(criteria.getId()).getDescription();

		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

}
