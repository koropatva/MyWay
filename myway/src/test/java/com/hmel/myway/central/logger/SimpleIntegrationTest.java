package com.hmel.myway.central.logger;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.exceptions.MyWayException;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleIntegrationTest {

	@Autowired
	private ICriteriaService iCriteriaService;

	@Test
	@Rollback(true)
	public void test() throws MyWayException {
			Criteria criteria = new Criteria();
			criteria.setDescription("Test description");
			criteria.setName("Test name");

			Criteria result = iCriteriaService.create(criteria);

			assertThat(result.getId()).describedAs("Id should be null")
					.isNotNull();
			assertThat(iCriteriaService.findOne(result.getId()))
					.describedAs(
							"Created criteria should be searched by id and not be NULL")
					.isNotNull();
			iCriteriaService.delete(result);
			assertThat(iCriteriaService.findOne(result.getId())).describedAs(
					"Created criteria should be NULL").isNull();

	}
}
