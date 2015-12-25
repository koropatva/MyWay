package com.hmel.myway.central.blogic.services;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.ICriteriaSynonymService;
import com.hmel.myway.central.models.CriteriaSynonym;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CriteriaSynonymServiceIntegrationTest extends
		BaseCRUDIntegrationTest<CriteriaSynonym> {

	private static final String TEST_UPDATED_DESCRIPTION = "Updated description";

	@Autowired
	private ICriteriaSynonymService iCriteriaSynonymService;

	@Override
	void setIService() {
		this.iService = iCriteriaSynonymService;
	}

	@Override
	void updateEntity(CriteriaSynonym result) {
		result.setDescription(TEST_UPDATED_DESCRIPTION);
	}

	@Override
	CriteriaSynonym createEntity() {
		return new CriteriaSynonym();
	}

	@Override
	void checkEntities(CriteriaSynonym result, CriteriaSynonym field) {
		assertThat(result).describedAs(
				"findOne should be searched by id and not be NULL").isNotNull();
		assertThat(result.getCriteria()).describedAs(
				"Criteria should be equals.").isEqualTo(field.getCriteria());
		assertThat(result.getRate()).describedAs("Rate should be equals.")
				.isEqualTo(field.getRate());
		assertThat(result.getModifiedTime()).describedAs(
				"ModifiedTimes shouldn't be null.").isNotNull();
		assertThat(result.getCreationTime()).describedAs(
				"CreationTimes shouldn't be null.").isNotNull();
	}

}
