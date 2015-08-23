package com.hmel.myway.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hmel.myway.central.blogic.interfaces.IAutosuggestService;
import com.hmel.myway.central.blogic.interfaces.IBlockSearchResponeService;
import com.hmel.myway.central.models.Autosuggest;
import com.hmel.myway.central.models.AutosuggestCriteria;
import com.hmel.myway.central.models.BlockSearchRespone;
import com.hmel.myway.central.models.CriteriaSearchHolder;
import com.hmel.myway.exceptions.MyWayException;

/**
 * @author Alexander Burkovskiy
 *
 */

@Controller
@RequestMapping(value = "/criteria")
public class BaseSearchController {

	private static final Logger logger = LoggerFactory.getLogger(BaseSearchController.class);

	@Autowired
	private IBlockSearchResponeService blockSearchResponeService;

	@Autowired
	private IAutosuggestService iAutosuggestService;

	@RequestMapping(value = "/search", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<List<BlockSearchRespone>> search(@RequestBody CriteriaSearchHolder holder) {
		logger.info("search");
		List<BlockSearchRespone> res = new ArrayList<>();
		try {
			res = blockSearchResponeService.findByCriteriaHolder(holder);
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<List<BlockSearchRespone>>(res, HttpStatus.OK);
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

}
