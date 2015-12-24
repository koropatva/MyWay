package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.ICriteriaPlaceService;
import com.hmel.myway.central.models.CriteriaPlace;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CriteriaPlaceServiceIntegrationTest extends
		BaseCRUDIntegrationTest<CriteriaPlace> {

	private static final String TEST_UPDATED_NAME = "Updated name";

	@Autowired
	private ICriteriaPlaceService iCriteriaPlaceService;

	@Override
	void setIService() {
		this.iService = iCriteriaPlaceService;
	}

	@Override
	void updateEntity(CriteriaPlace result) {
		result.setName(TEST_UPDATED_NAME);
	}

	@Override
	CriteriaPlace createEntity() {
		return new CriteriaPlace();
	}

	@Override
	void checkEntities(CriteriaPlace result, CriteriaPlace field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getName()).describedAs("Name should be equals.")
				.isEqualTo(field.getName());
		assertThat(result.getPlace()).describedAs("Places should be equals.")
				.isEqualTo(field.getPlace());
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes shouldn't be null.").isNotNull();
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes shouldn't be null.").isNotNull();
	}

}
