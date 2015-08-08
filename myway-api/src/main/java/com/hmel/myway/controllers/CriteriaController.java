package com.hmel.myway.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.hmel.myway.central.blogic.interfaces.IAutosuggestService;
import com.hmel.myway.central.blogic.interfaces.IBlockSearchResponeService;
import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Autosuggest;
import com.hmel.myway.central.models.AutosuggestCriteria;
import com.hmel.myway.central.models.BlockSearchRespone;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.central.models.CriteriaSearchHolder;
import com.hmel.myway.exceptions.MyWayException;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/criteria")
public class CriteriaController {

	@Autowired
	private ICriteriaService iCriteriaService;

	@Autowired
	private IAutosuggestService iAutosuggestService;
	
	@Autowired
	private IBlockSearchResponeService blockSearchResponeService;

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

	@RequestMapping(value = "/autosuggest", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<Autosuggest> findAutosuggest(@RequestBody AutosuggestCriteria autosuggestCriteria) {
		logger.info("find Autosuggest by params");
		Autosuggest res = new Autosuggest();
		try {
			res = iAutosuggestService.findByParams(autosuggestCriteria);
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<Autosuggest>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<List<BlockSearchRespone>>  search(@RequestBody CriteriaSearchHolder holder) {
		logger.info("search");
		List<BlockSearchRespone> res = new ArrayList<>();
		try {
			res = blockSearchResponeService.findByCriteriaHolder(holder);
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<List<BlockSearchRespone>>(res, HttpStatus.OK);
	}

}