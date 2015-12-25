package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.dao.blogic.interfaces.IEntity;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

abstract public class BaseCRUDIntegrationTest<T extends IEntity> {

	private static final int DEFAULT_ARRAY_SIZE = 1;

	private static final Logger logger = LoggerFactory
			.getLogger(BaseCRUDIntegrationTest.class);

	protected T entity;

	protected IHibernateDAO<T, Long> iService;

	abstract void checkEntities(T result, T field);

	abstract T createEntity();

	abstract void updateEntity(T result);

	abstract void setIService();

	@Before
	public void setUp() throws MyWayException {
		setIService();

		entity = createEntity();

		entity = iService.create(entity);
		assertThat(entity.getId()).describedAs("Id shouldn't be null")
				.isNotNull();
	}

	@After
	public void tearDown() throws MyWayException {
		iService.delete(entity);
		assertThat(iService.findOne(entity.getId())).describedAs(
				"Criteria should not exist").isNull();
	}

	@Test
	public void testCRUD() throws MyWayException {
		logger.info("testCRUD CALLED");
		T result = iService.findOne(entity.getId());
		checkEntities(result, entity);

		updateEntity(entity);
		iService.update(entity);
		result = iService.findOne(entity.getId());
		checkEntities(result, entity);

	}

	@Test
	public void testDeleteById() throws MyWayException {
		logger.info("testDeleteById CALLED");

		T deleteEntity = createEntity();
		deleteEntity = iService.create(deleteEntity);

		iService.deleteById(deleteEntity.getId());
		assertThat(iService.findOne(deleteEntity.getId())).describedAs(
				"Criteria should not exist").isNull();
	}

	@Test
	@Ignore
	public void testDetachedCriteria() throws MyWayException {
		logger.info("testDetachedCriteria CALLED");
		// TODO
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Criteria.class);
		detachedCriteria.add(Restrictions.eq("id", entity.getId()));

		List<T> entities = iService.findByCriteria(detachedCriteria);

		assertThat(entities)
				.describedAs(
						"findByCriteria should be searched and find list with lenght equals 1")
				.isNotNull().asList().hasSize(1);

	}

	@Test
	public void testFindAll() throws MyWayException {
		logger.info("testFindAll CALLED");
		List<T> list = iService.findAll(0, DEFAULT_ARRAY_SIZE);
		assertThat(list).as("").isNotNull().asList()
				.hasSize(DEFAULT_ARRAY_SIZE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindAllWithMinusFirstResult() throws MyWayException {
		logger.info("testFindAllWithMinusFirstResult CALLED");
		iService.findAll(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindAllWithMinusMaxResult() throws MyWayException {
		logger.info("testFindAllWithMinusMaxResult CALLED");
		iService.findAll(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDetachedCriteriaWithEmptyCriteria() throws MyWayException {
		logger.info("testDetachedCriteriaWithEmptyCriteria CALLED");
		iService.findByCriteria(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDetachedCriteriaWithMinusFirstResult()
			throws MyWayException {
		logger.info("testDetachedCriteriaWithMinusFirstResult CALLED");
		iService.findByCriteria(DetachedCriteria.forClass(Criteria.class), -1,
				0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDetachedCriteriaWithMinusMaxResult() throws MyWayException {
		logger.info("testDetachedCriteriaWithMinusMaxResult CALLED");
		iService.findByCriteria(DetachedCriteria.forClass(Criteria.class), 0,
				-1);
	}

}
