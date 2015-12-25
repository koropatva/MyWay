package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.IPlaceService;
import com.hmel.myway.central.models.Place;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PlaceServiceIntegrationTest extends BaseCRUDIntegrationTest<Place> {

	private static final String TEST_UPDATED_NAME = "Updated name";

	@Autowired
	private IPlaceService iPlaceService;

	@Override
	void setIService() {
		this.iService = iPlaceService;
	}

	@Override
	void updateEntity(Place result) {
		result.setName(TEST_UPDATED_NAME);
	}

	@Override
	Place createEntity() {
		return new Place();
	}

	@Override
	void checkEntities(Place result, Place field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getCity()).describedAs("City should be equals.")
				.isEqualTo(field.getCity());
		assertThat(result.getCountry())
				.describedAs("Country should be equals.").isEqualTo(
						field.getCountry());
		assertThat(result.getName()).describedAs("Name should be equals.")
				.isEqualTo(field.getName());
		assertThat(result.getX()).describedAs("X should be equals.").isEqualTo(
				field.getX());
		assertThat(result.getY()).describedAs("Y should be equals.").isEqualTo(
				field.getY());
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes shouldn't be null.").isNotNull();
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes shouldn't be null.").isNotNull();
	}

}
