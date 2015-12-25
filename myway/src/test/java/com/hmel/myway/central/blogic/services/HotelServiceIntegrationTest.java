package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.IHotelService;
import com.hmel.myway.central.models.Hotel;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelServiceIntegrationTest extends BaseCRUDIntegrationTest<Hotel> {

	private static final String TEST_UPDATED_NAME = "Updated name";

	@Autowired
	private IHotelService iHotelService;

	@Override
	void setIService() {
		this.iService = iHotelService;
	}

	@Override
	void updateEntity(Hotel result) {
		result.setName(TEST_UPDATED_NAME);
	}

	@Override
	Hotel createEntity() {
		return new Hotel();
	}

	@Override
	void checkEntities(Hotel result, Hotel field) {
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
