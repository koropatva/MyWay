package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.central.models.Criteria;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CriteriaServiceIntegrationTest extends
		BaseCRUDIntegrationTest<Criteria> {

	private static final String TEST_UPDATED_NAME = "Updated name";

	@Autowired
	private ICriteriaService iCriteriaService;

	@Override
	void setIService() {
		this.iService = iCriteriaService;
	}

	@Override
	void updateEntity(Criteria result) {
		result.setName(TEST_UPDATED_NAME);
	}

	@Override
	Criteria createEntity() {
		return new Criteria();
	}

	@Override
	void checkEntities(Criteria result, Criteria field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getName()).describedAs("Names should be equals.")
				.isEqualTo(field.getName());
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes shouldn't be null.").isNotNull();
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes shouldn't be null.").isNotNull();
	}

}
