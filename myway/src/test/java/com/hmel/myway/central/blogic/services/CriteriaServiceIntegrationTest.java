package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.enums.CriteriaType;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.exceptions.PhoneDictionaryException;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CriteriaServiceIntegrationTest {

	private static final Logger logger = LoggerFactory
			.getLogger(CriteriaServiceIntegrationTest.class);

	private static final String TEST_DESCRIPTION = "Test description";
	private static final String TEST_NAME = "Test name";
	private static final String TEST_UPDATED_NAME = "Updated name";

	private Date creationTime = new Date();
	private Date modifiedTime = new Date();
	private CriteriaType type = CriteriaType.MONEY;
	private Criteria criteria;

	@Autowired
	private ICriteriaService iCriteriaService;

	@Before
	public void setUp() throws PhoneDictionaryException {
		criteria = new Criteria();
		criteria.setDescription(TEST_DESCRIPTION);
		criteria.setName(TEST_NAME);
		criteria.setType(type);

		criteria = iCriteriaService.create(criteria);
		assertThat(criteria.getId()).describedAs("Id should be null")
				.isNotNull();
	}

	@After
	public void tearDown() throws PhoneDictionaryException {
		iCriteriaService.delete(criteria);
		assertThat(iCriteriaService.findOne(criteria.getId())).describedAs(
				"Criteria should not exist").isNull();
	}
	
	@Test
	public void testCRUD() throws PhoneDictionaryException {
		logger.info("testCRUD CALLED");
		Criteria result = iCriteriaService.findOne(criteria.getId());
		checkCriterias(result, criteria);

		criteria.setName(TEST_UPDATED_NAME);
		iCriteriaService.update(criteria);
		result = iCriteriaService.findOne(criteria.getId());
		checkCriterias(result, criteria);

	}

	@Test
	public void testDeleteById() throws PhoneDictionaryException {
		logger.info("testDeleteById CALLED");
		Criteria criteriaLocal = new Criteria();
		criteriaLocal = iCriteriaService.create(criteriaLocal);

		iCriteriaService.deleteById(criteriaLocal.getId());
		assertThat(iCriteriaService.findOne(criteriaLocal.getId()))
				.describedAs("Criteria should not exist").isNull();
	}

	@Test
	public void testDetachedCriteria() throws PhoneDictionaryException {
		logger.info("testDetachedCriteria CALLED");
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Criteria.class);
		detachedCriteria.add(Restrictions.eq("id", criteria.getId()));

		List<Criteria> criterias = iCriteriaService
				.findByCriteria(detachedCriteria);

		assertThat(criterias)
				.describedAs(
						"findByCriteria should be searched and find list with lenght equals 1")
				.isNotNull().asList().hasSize(1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testDetachedCriteriaWithEmptyCriteria()
			throws PhoneDictionaryException {
		logger.info("testDetachedCriteriaWithEmptyCriteria CALLED");
		iCriteriaService.findByCriteria(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDetachedCriteriaWithMinusFirstResult()
			throws PhoneDictionaryException {
		logger.info("testDetachedCriteriaWithMinusFirstResult CALLED");
		iCriteriaService.findByCriteria(
				DetachedCriteria.forClass(Criteria.class), -1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDetachedCriteriaWithMinusMaxResult()
			throws PhoneDictionaryException {
		logger.info("testDetachedCriteriaWithMinusMaxResult CALLED");
		iCriteriaService.findByCriteria(
				DetachedCriteria.forClass(Criteria.class), 0, -1);
	}

	private void checkCriterias(Criteria result, Criteria field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getDescription()).describedAs(
				"Descriptions should be equals.").isEqualTo(
				field.getDescription());
		assertThat(result.getName()).describedAs("Names should be equals.")
				.isEqualTo(field.getName());
		assertThat(result.getType()).describedAs("Types should be equals.")
				.isEqualTo(field.getType());
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes should be equals.").isNotNull();
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes should be equals.").isNotNull();
	}
}
