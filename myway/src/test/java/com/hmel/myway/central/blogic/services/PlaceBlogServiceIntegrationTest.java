package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.IPlaceBlogService;
import com.hmel.myway.central.models.PlaceBlog;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PlaceBlogServiceIntegrationTest extends
		BaseCRUDIntegrationTest<PlaceBlog> {

	private static final String TEST_UPDATED_BLOG_URL = "Updated blog url";

	@Autowired
	private IPlaceBlogService iPlaceBlogService;

	@Override
	void setIService() {
		this.iService = iPlaceBlogService;
	}

	@Override
	void updateEntity(PlaceBlog result) {
		result.setBlogURL(TEST_UPDATED_BLOG_URL);
	}

	@Override
	PlaceBlog createEntity() {
		return new PlaceBlog();
	}

	@Override
	void checkEntities(PlaceBlog result, PlaceBlog field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getBlogURL())
				.describedAs("BlogURL should be equals.").isEqualTo(
						field.getBlogURL());
		assertThat(result.getPlace()).describedAs("Place should be equals.")
				.isEqualTo(field.getPlace());
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes shouldn't be null.").isNotNull();
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes shouldn't be null.").isNotNull();
	}

}
