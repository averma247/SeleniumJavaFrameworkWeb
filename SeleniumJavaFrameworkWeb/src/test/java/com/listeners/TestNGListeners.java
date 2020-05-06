package com.listeners;

import org.apache.logging.log4j.LogManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.library.GenericFunctionLib;
import com.utils.ExtentTestReportManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners extends GenericFunctionLib implements ITestListener {
	private static	Logger logger = LogManager.getLogger(TestNGListeners.class);

	ExtentReports extent =ExtentTestReportManager.extentRepotGeneratorNG();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("***** Test Started *******"+ result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("***** Test Failed But with in success percentage *******"+ result.getMethod());

	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		logger.info("***** Test Failed *******"+ result.getMethod());
		test.fail(result.getThrowable());
		Object testObject=result.getInstance();
		Class testclass=result.getTestClass().getRealClass();


		try {
			driver=(WebDriver)testclass.getDeclaredField("driver").get(testObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		try {
			test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("***** Test Skipped *******"+ result.getMethod());

	}


	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("***** Test Successfull *******"+ result.getMethod().getMethodName());
		test.log(Status.PASS, "Test is passed successfully ");
	}
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

		System.out.println("Inside onStartMethod(ITestContext context)");

	}
	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		System.out.println("Inside onFinish(ITestContext context)");
		extent.flush();
	}


}
