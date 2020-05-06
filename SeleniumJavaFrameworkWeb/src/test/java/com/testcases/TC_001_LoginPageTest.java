package com.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.library.GenericFunctionLib;

public class TC_001_LoginPageTest extends GenericFunctionLib{
	
	public WebDriver driver;
	
  @Test
  public void verifyValidUsersPass(){
	  
	  driver= initializeDriver();
	  driver.get(configprop.getProperty("baseURL"));
	  System.out.println("Inside verifyValidUsersPass() Test Case");
	  Assert.assertTrue(true);
	  System.out.println("Test Case is passed");
  }
  
  @Test
  public void verifyValidUsersFail(){
	  
	  driver= initializeDriver();
	  driver.get(configprop.getProperty("baseURL"));
	  System.out.println("Inside verifyValidUsersFail() Test Case");
	  Assert.assertTrue(false);
	  System.out.println("Test Case is Failed");
	  
  }
  
  @AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();

	}

}
