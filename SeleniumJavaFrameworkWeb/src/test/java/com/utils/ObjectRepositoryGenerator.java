package com.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectRepositoryGenerator {

	private static	Logger logger = LogManager.getLogger(ObjectRepositoryGenerator.class);

	public static void main(String[] args) {
		
		Properties prop=getORProperties(System.getProperty("user.dir")+"/src/test/resources/objectrepository.properties");
		logger.info("Xpath value: "+ prop.getProperty("xpathvalue"));
		logger.info("Config file read successfully");
	}
	
	
	/** 
	* This function get the Configuration from object repository file and can be used as 
	* per requirement.
	*/
	
	public static Properties getORProperties(String filePath){
		Properties prop = new Properties();
		try {

			logger.info("Goin to read object repository file");
			InputStream input = new FileInputStream(filePath);
			logger.info("Object repository file read successfully");
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
