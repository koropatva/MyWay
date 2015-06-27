package com.hmel.myway.central.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

	private static final Logger logger = LoggerFactory
			.getLogger(LoggerTest.class);

	@Test
	public void runLogger() {
		assert logger != null;
		logger.info("Hello {} ", "Sasha");
		logger.error("Hello {} ", "Sasha");
		logger.debug("Hello {} ", "Sasha");
		logger.warn("Hello {} ", "Sasha");
	}

}
