package com.hmel.myway.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hmel.myway.dao.blogic.interfaces.IEntity;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

public abstract class BaseController<T extends IEntity, P extends Serializable> {

	protected IHibernateDAO<T, P> iHibernateDAO;

	protected abstract void setIHibernateDAO();
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@PostConstruct
	protected void setUp(){
		setIHibernateDAO();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<T> save(@RequestBody T entity) throws MyWayException {
		try {
			entity = iHibernateDAO.save(entity);
			entity = iHibernateDAO.findOne((P) entity.getId());
			logger.info("Save block: " + entity.toString());
			return new ResponseEntity<T>(entity, HttpStatus.OK);
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
			throw new MyWayException();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<T> update(@RequestBody T entity) throws MyWayException {
		try {
			if (entity.getId() != 0) {
				entity = iHibernateDAO.update(entity);
				entity = iHibernateDAO.findOne((P) entity.getId());
				logger.info("Update block: " + entity.toString());
				return new ResponseEntity<T>(entity, HttpStatus.OK);
			} else {
				return new ResponseEntity<T>(entity, HttpStatus.BAD_REQUEST);
			}
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
			throw new MyWayException();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "content-type=application/json")
	public ResponseEntity<T> get(@PathVariable("id") Long id) throws MyWayException {
		try {
			T entity = iHibernateDAO.findOne((P) id);

			logger.info("Get block: " + entity.toString());
			return new ResponseEntity<T>(entity, HttpStatus.OK);
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
			throw new MyWayException();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "content-type=application/json")
	public ResponseEntity<T> delete(@PathVariable("id") Long id) throws MyWayException {
		try {
			iHibernateDAO.deleteById((P) id);
			logger.info("Delete block by ID: " + id);
			return new ResponseEntity<T>(HttpStatus.OK);
		} catch (MyWayException e) {
			logger.error(e.getMessage(), e);
			throw new MyWayException();
		}
	}
}
