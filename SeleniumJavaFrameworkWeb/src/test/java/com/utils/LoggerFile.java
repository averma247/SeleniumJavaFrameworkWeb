package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerFile {

	private static	Logger logger = LogManager.getLogger(LoggerFile.class);

	public static void main(String[] args) {

		System.out.println("Hello World.!............\n");
		logger.trace("This is a trace message");
		logger.info("This is information method");
		logger.warn("This is warning message");
		logger.fatal("This is fatal error message");
		logger.error("This is error log");
		logger.debug("This is a debug message");
		System.out.println("\n Completed");

	}
	
	public static void loggerTest() {

		System.out.println("Hello World.!............\n");
		logger.trace("This is a trace message");
		logger.info("This is information method");
		logger.warn("This is warning message");
		logger.fatal("This is fatal error message");
		logger.error("This is error log");
		logger.debug("This is a debug message");
		System.out.println("\n Completed");

	}

}
