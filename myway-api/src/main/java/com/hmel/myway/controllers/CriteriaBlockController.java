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

import com.hmel.myway.central.blogic.interfaces.ICriteriaBlockService;
import com.hmel.myway.central.models.CriteriaBlock;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/criteria_block")
public class CriteriaBlockController {

	@Autowired
	private ICriteriaBlockService iCriteriaBlockService;

	private static final Logger logger = LoggerFactory
			.getLogger(CriteriaBlockController.class);

	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<CriteriaBlock> save(
			@RequestBody CriteriaBlock criteriaBlock)
			throws PhoneDictionaryException {
		try {
			criteriaBlock = iCriteriaBlockService.save(criteriaBlock);
			criteriaBlock = iCriteriaBlockService
					.findOne(criteriaBlock.getId());
			logger.info("Save block: " + criteriaBlock.toString());
			return new ResponseEntity<CriteriaBlock>(criteriaBlock,
					HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<CriteriaBlock> update(
			@RequestBody CriteriaBlock criteriaBlock)
			throws PhoneDictionaryException {
		try {
			if (criteriaBlock.getId() != 0) {
				criteriaBlock = iCriteriaBlockService.update(criteriaBlock);
				criteriaBlock = iCriteriaBlockService.findOne(criteriaBlock
						.getId());
				logger.info("Update block: " + criteriaBlock.toString());
				return new ResponseEntity<CriteriaBlock>(criteriaBlock,
						HttpStatus.OK);
			} else {
				logger.info("Bad request for update block: "
						+ criteriaBlock.toString());
				return new ResponseEntity<CriteriaBlock>(criteriaBlock,
						HttpStatus.BAD_REQUEST);
			}
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<CriteriaBlock> get(@PathVariable("id") Long id)
			throws PhoneDictionaryException {
		try {
			CriteriaBlock criteriaBlock = iCriteriaBlockService.findOne(id);
			logger.info("Get block: " + criteriaBlock.toString());
			return new ResponseEntity<CriteriaBlock>(criteriaBlock,
					HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "content-type=application/json")
	public ResponseEntity<CriteriaBlock> delete(@PathVariable("id") Long id)
			throws PhoneDictionaryException {
		try {
			iCriteriaBlockService.deleteById(id);
			logger.info("Delete block by ID: " + id.toString());
			return new ResponseEntity<CriteriaBlock>(HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

}
