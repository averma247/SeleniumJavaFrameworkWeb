package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testcases.Test99GuruLogin;

public class Guru99Login {
	private static	Logger logger = LogManager.getLogger(Guru99Login.class);

    WebDriver driver;

    By user99GuruName = By.name(Test99GuruLogin.objRepository.getProperty("userid"));

    By password99Guru = By.name(Test99GuruLogin.objRepository.getProperty("password"));

    By titleText =By.className(Test99GuruLogin.objRepository.getProperty("titleText"));

    By loginButton = By.name(Test99GuruLogin.objRepository.getProperty("btnLogin"));

    public Guru99Login(WebDriver driver){

        this.driver = driver;

    }

    //Set user name in textbox

    public void setUserName(String strUserName){
    	logger.debug("Setting user name "+ strUserName +" in User Name textbox");
        driver.findElement(user99GuruName).sendKeys(strUserName);

    }

    //Set password in password textbox

    public void setPassword(String strPassword){
    	logger.debug("Setting password "+ strPassword +" in Password textbox");
         driver.findElement(password99Guru).sendKeys(strPassword);

    }

    //Click on login button

    public void clickLogin(){
    		logger.debug("Going to click on Login Button");
            driver.findElement(loginButton).click();
            logger.debug("Clicked on Login Button");

    }

    //Get the title of Login Page

    public String getLoginTitle(){

     return    driver.findElement(titleText).getText();

    }

    /**

     * This POM method will be exposed in test case to login in the application

     * @param strUserName

     * @param strPasword

     * @return

     */

    public void loginToGuru99(String strUserName,String strPasword){

        //Fill user name

        this.setUserName(strUserName);

        //Fill password

        this.setPassword(strPasword);

        //Click Login button

        this.clickLogin();        
    }

}