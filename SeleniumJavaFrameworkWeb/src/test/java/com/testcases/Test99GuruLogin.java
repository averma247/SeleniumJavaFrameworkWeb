package com.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.library.GenericFunctionLib;
import com.pages.Guru99HomePage;
import com.pages.Guru99Login;
import com.utils.ConfigGenerator;
import com.utils.ExcelDataProvider;
import com.utils.ObjectRepositoryGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Test99GuruLogin {

	static ExtentTest extentTestLogger;
	static ExtentReports report;
	static ExtentReports extent;
	static WebDriver driver=null;
	static String browser=null;
	public static Properties configprop=null;
	public static Properties objRepository=null;
	private static	Logger logger = LogManager.getLogger(Test99GuruLogin.class);


	@BeforeClass
	public static void startTest()
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/ExtentReportResults.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("os", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", configprop.getProperty("environment"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		htmlReporter.config().setDocumentTitle("Final Execution Report");
		htmlReporter.config().setReportName("Selenium Web Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}



	@BeforeTest
	public void beforeTest() {

		configprop=ConfigGenerator.getProperties(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		objRepository=ObjectRepositoryGenerator.getORProperties(System.getProperty("user.dir")+"/src/test/resources/objectrepository.properties");
		//- Below code is to set Browser based on selection -//

		browser=configprop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")){
			/*String projectpath= System.getProperty("user.dir");

			//System.setProperty("webdriver.chrome.driver",projectpath+"/drivers/chromedriver/chromedriver.exe");
			 */			
			WebDriverManager.chromedriver().version(configprop.getProperty("browserversion")).setup(); //best options
			driver = new ChromeDriver();


		}//-- end of -if ---//

		else{

			System.out.println("Please provide Browser");
			logger.error("Please provide Browser");
		}
		driver.manage().window().maximize();



	}


	/**

	 * This test case will login in http://demo.guru99.com/V4/

	 * Verify login page title as guru99 bank

	 * Login to application

	 * Verify the home page using Dashboard message

	 */

	@Test(dataProvider = "logindata",description = "Test verify the login Page functionality")
	public void verifyLoginPage(HashMap<Object,Object>data) throws InterruptedException {

		driver.get(configprop.getProperty("baseURL"));

		extentTestLogger = extent.createTest("VerifyLoginPage","Test verify the login Page functionality");

		//logger.info(data.get("UserName").toString()+" | "+ 	data.get("Password").toString());
		//Create Login Page object
		extentTestLogger.log(Status.INFO,"Browser open and going to "+configprop.getProperty("baseURL"));
		logger.debug("Create Login Page object");
		Guru99Login objLogin = new Guru99Login(driver);

		//Verify login page title
		logger.debug("Verify login page title");
		extentTestLogger.log(Status.INFO,"Verify login page title");
		String loginPageTitle = objLogin.getLoginTitle();

		Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
		extentTestLogger.log(Status.INFO,"Login page title is verified: "+ loginPageTitle);
		//login to application

		logger.debug("login to application");
		objLogin.loginToGuru99(data.get("UserName").toString(), data.get("Password").toString());

		// go the next page
		logger.debug("Go the next page");

		Guru99HomePage  objHomePage = new Guru99HomePage(driver);

		//Verify home page
		logger.debug("Verify home page");

		Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : "+data.get("UserName")));
		logger.debug("Test is verified.");
		extentTestLogger.log(Status.PASS,"Test is verified and UserName is visible on UI: ");

	}


	

	@AfterMethod
	public void afterMethod() {

		logger.debug("Going to close the browser and perform the cleaning process");
		driver.close();
		driver.quit();

	}

	@DataProvider(name="logindata")
	public Object[][] getTestData(){

		String projectPath= System.getProperty("user.dir");
		String excelPath=projectPath+"/databank/data.xlsx";		
		Object data[][]=ExcelDataProvider.testData(excelPath, "LoginDetails");
		return data;
	}


}
