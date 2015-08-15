package com.hmel.myway.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/criteria")
public class CriteriaController {

	@Autowired
	private ICriteriaService iCriteriaService;
	
	private static final Logger logger = LoggerFactory.getLogger(CriteriaController.class);

	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<Criteria> save(@RequestBody Criteria criteria) throws PhoneDictionaryException {
		try {
			criteria = iCriteriaService.save(criteria);
			criteria = iCriteriaService.findOne(criteria.getId());
			logger.info("Save criteria: " + criteria.toString());
			return new ResponseEntity<Criteria>(criteria, HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<Criteria> update(@RequestBody Criteria criteria) throws PhoneDictionaryException {
		try {
			if (criteria.getId() != 0) {
				criteria = iCriteriaService.update(criteria);
				criteria = iCriteriaService.findOne(criteria.getId());
				logger.info("Update block: " + criteria.toString());
				return new ResponseEntity<Criteria>(criteria, HttpStatus.OK);
			} else {
				logger.info("Badrequest for update criteria: " + criteria.toString());
				return new ResponseEntity<Criteria>(criteria, HttpStatus.BAD_REQUEST);
			}
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<Criteria> get(@PathVariable("id") Long id) throws PhoneDictionaryException {
		try {
			Criteria criteria = iCriteriaService.findOne(id);
			logger.info("Get criteria: " + criteria.toString());
			return new ResponseEntity<Criteria>(criteria, HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "content-type=application/json")
	public ResponseEntity<Criteria> delete(@PathVariable("id") Long id) throws PhoneDictionaryException {
		try {
			iCriteriaService.deleteById(id);
			logger.info("Delete criteria by ID: " + id.toString());
			return new ResponseEntity<Criteria>(HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

}