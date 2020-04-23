package com.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigGenerator {
	private static	Logger logger = LogManager.getLogger(ConfigGenerator.class);

	public static void main(String[] args) {
		
		Properties prop=getProperties(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		logger.info("Browser name is: "+ prop.getProperty("browser"));
		logger.info("Config file read successfully");
	}
	
	
	/** 
	* This function get the Configuration from Config files and can be used as 
	* per requirement.
	*/
	
	public static Properties getProperties(String filePath){
		Properties prop = new Properties();
		try {

			logger.info("Goin to read Config file");
			InputStream input = new FileInputStream(filePath);
			logger.info("Config file read successfully");
			prop.load(input);
			logger.info("==== Config paramaters =========");
			logger.info(Arrays.asList(prop));
			logger.info("================================");


		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}

		return prop;
	}
}
