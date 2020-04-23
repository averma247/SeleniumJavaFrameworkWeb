package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.testcases.Test99GuruLogin;

public class Guru99HomePage {
	private static	Logger logger = LogManager.getLogger(Test99GuruLogin.class);
	WebDriver driver;

	By homePageUserName = By.xpath(Test99GuruLogin.objRepository.getProperty("homePageUserName"));



	public Guru99HomePage(WebDriver driver){

		this.driver = driver;

	}

	//Get the User name from Home Page

	public String getHomePageDashboardUserName(){

		logger.debug("User name from Home Page: " + driver.findElement(homePageUserName).getText());
		return    driver.findElement(homePageUserName).getText();

	}

}