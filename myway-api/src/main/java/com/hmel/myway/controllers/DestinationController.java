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

import com.hmel.myway.central.blogic.interfaces.IDestinationService;
import com.hmel.myway.central.models.Destination;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/destination")
public class DestinationController {

	@Autowired
	private IDestinationService iDestinationService;

	private static final Logger logger = LoggerFactory
			.getLogger(DestinationController.class);

	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<Destination> save(@RequestBody Destination destination)
			throws PhoneDictionaryException {
		try {
			destination = iDestinationService.save(destination);
			destination = iDestinationService.findOne(destination.getId());
			logger.info("Save destination: " + destination.toString());
			return new ResponseEntity<Destination>(destination, HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<Destination> update(
			@RequestBody Destination destination)
			throws PhoneDictionaryException {
		try {
			if (destination.getId() != 0) {
				destination = iDestinationService.update(destination);
				destination = iDestinationService.findOne(destination.getId());
				logger.info("Update destination: " + destination.toString());
				return new ResponseEntity<Destination>(destination,
						HttpStatus.OK);
			} else {
				logger.info("Bad request for update destination: "
						+ destination.toString());
				return new ResponseEntity<Destination>(destination,
						HttpStatus.BAD_REQUEST);
			}
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, headers = "content-type=application/json")
	  public ResponseEntity<List<Destination>> getAll() {
		logger.info("Get all destination ");
		List<Destination> res = new ArrayList<>();
		try {
			res = iDestinationService.findAll(0,Integer.MAX_VALUE);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<List<Destination>>(res, HttpStatus.OK);
	  }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<Destination> get(@PathVariable("id") Long id)
			throws PhoneDictionaryException {
		logger.info("Get destination by ID: " + id);
		try {
			Destination destination = iDestinationService.findOne(id);
			return new ResponseEntity<Destination>(destination, HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "content-type=application/json")
	public ResponseEntity<Destination> delete(@PathVariable("id") Long id)
			throws PhoneDictionaryException {
		try {
			iDestinationService.deleteById(id);
			logger.info("Delete destination by ID: " + id.toString());
			return new ResponseEntity<Destination>(HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

}
