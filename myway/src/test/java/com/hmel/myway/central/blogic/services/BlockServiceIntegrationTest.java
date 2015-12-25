package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.IBlockService;
import com.hmel.myway.central.models.Block;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BlockServiceIntegrationTest extends BaseCRUDIntegrationTest<Block> {

	private static final String TEST_UPDATED_SHORT_DESCRIPTION = "Updated description";

	@Autowired
	private IBlockService iBlockService;

	@Override
	void setIService() {
		this.iService = iBlockService;
	}

	@Override
	void updateEntity(Block result) {
		result.setShortDescription(TEST_UPDATED_SHORT_DESCRIPTION);
	}
	
	@Override
	Block createEntity() {
		return new Block();
	}

	@Override
	void checkEntities(Block result, Block field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getDescription()).describedAs(
				"Descriptions should be equals.").isEqualTo(
				field.getDescription());
		assertThat(result.getShortDescription()).describedAs(
				"Short Descriptions should be equals.").isEqualTo(
				field.getShortDescription());
		assertThat(result.getPlace()).describedAs("Places should be equals.")
				.isEqualTo(field.getPlace());
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes shouldn't be null.").isNotNull();
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes shouldn't be null.").isNotNull();
	}

}
