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

import com.hmel.myway.central.blogic.interfaces.IBlockService;
import com.hmel.myway.central.conditioholder.BlockConditionHolder;
import com.hmel.myway.central.models.Block;
import com.hmel.myway.exceptions.PhoneDictionaryException;

/**
 * @author Burkovskiy Alexander
 */

@Controller
@RequestMapping(value = "/block")
public class BlockController {

	@Autowired
	private IBlockService iBlockService;

	private static final Logger logger = LoggerFactory
			.getLogger(BlockController.class);

	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<Block> save(@RequestBody Block block)
			throws PhoneDictionaryException {
		try {
			block = iBlockService.save(block);
			block = iBlockService.findOne(block.getId());
			logger.info("Save block: " + block.toString());
			return new ResponseEntity<Block>(block, HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<Block> update(@RequestBody Block block)
			throws PhoneDictionaryException {
		try {
			if (block.getId() != 0) {
				block = iBlockService.update(block);
				block = iBlockService.findOne(block.getId());
				logger.info("Update block: " + block.toString());
				return new ResponseEntity<Block>(block, HttpStatus.OK);
			} else {
				return new ResponseEntity<Block>(block, HttpStatus.BAD_REQUEST);
			}
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<Block> get(@PathVariable("id") Long id)
			throws PhoneDictionaryException {
		try {
			Block block = iBlockService.findOne(id);
			
			logger.info("Get block: " + block.toString());
			return new ResponseEntity<Block>(block, HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "content-type=application/json")
	public ResponseEntity<Block> delete(@PathVariable("id") Long id)
			throws PhoneDictionaryException {
		try {
			iBlockService.deleteById(id);
			logger.info("Delete block by ID: " + id);
			return new ResponseEntity<Block>(HttpStatus.OK);
		} catch (PhoneDictionaryException e) {
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
	}
	
	@RequestMapping(value = "/searchlst", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<List<Block>> search(@RequestBody BlockConditionHolder holder) throws PhoneDictionaryException {
		logger.info("search");
		List<Block> res = new ArrayList<>();
		try{
			res = iBlockService.findByConditionHolder(holder);
			return new ResponseEntity<List<Block>>(res, HttpStatus.OK);
		}catch(PhoneDictionaryException e){
			logger.error(e.getMessage(), e);
			throw new PhoneDictionaryException();
		}
		
	}

}
