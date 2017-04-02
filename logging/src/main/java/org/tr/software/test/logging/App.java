package org.tr.software.test.logging;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	public static void main(String[] args) throws Exception{
		//System.out.println(System.getProperty("catalina.base"));

		//Logger logger = LoggerFactory.getLogger(App.class);
		Logger logger = LoggerFactory.getLogger(LogManager.ROOT_LOGGER_NAME);
		
//		ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF
		
		try {
			System.out.println(1/0);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Appending string: {}.", e.getMessage());
		}
		logger.debug("This will be printed on debug");
		logger.info("This will be printed on info");
		logger.warn("This will be printed on warn");
		logger.error("Appending string: {}.", "Hello, World");
//		logger.fatal("This will be printed on fatal");
//		logger.trace("This will be printed on trace");
	}
}
