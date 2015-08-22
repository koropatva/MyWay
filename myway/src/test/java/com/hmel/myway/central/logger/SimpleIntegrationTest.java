package com.hmel.myway.central.logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hmel.myway.central.blogic.interfaces.ICriteriaService;
import com.hmel.myway.exceptions.PhoneDictionaryException;

@ContextConfiguration(locations = "/spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleIntegrationTest {

	@Autowired
	private ICriteriaService iCriteriaService;

	@Test
	public void test() {
		try {
			iCriteriaService.findAll();
		} catch (PhoneDictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
