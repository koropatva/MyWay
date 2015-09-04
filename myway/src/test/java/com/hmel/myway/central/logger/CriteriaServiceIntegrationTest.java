package com.hmel.myway.central.logger;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Autowired
	private ICriteriaService iCriteriaService;

	@Test
	public void crudTest() {
		try {
			Criteria criteria = new Criteria();
			criteria.setCreationTime(new Date());
			criteria.setDescription("Test description");
			criteria.setModifiedTime(new Date());
			criteria.setName("Test name");
			criteria.setType(CriteriaType.MONEY);

			Criteria result = iCriteriaService.create(criteria);

			assertThat(result.getId()).describedAs("Id should be null")
					.isNotNull();
			assertThat(iCriteriaService.findOne(result.getId())).describedAs(
					"findOne should be searched by id and not be NULL")
					.isNotNull();

			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(Criteria.class);
			detachedCriteria.add(Restrictions.eq("name", result.getName()));

			List<Criteria> criterias = iCriteriaService
					.findByCriteria(detachedCriteria);

			assertThat(criterias)
					.describedAs(
							"findByCriteria should be searched and find list with lenght equals 1")
					.isNotNull().asList().isNotEmpty();

			iCriteriaService.delete(result);
			assertThat(iCriteriaService.findOne(result.getId())).describedAs(
					"Created criteria should be NULL").isNull();

		} catch (PhoneDictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
