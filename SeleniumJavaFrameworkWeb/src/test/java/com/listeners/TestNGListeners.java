package com.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {
	private static	Logger logger = LogManager.getLogger(TestNGListeners.class);
	public void onFinish(ITestContext result) {

		logger.info("***** Test Completed *******"+ result.getName());
		
	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
		logger.info("***** Test Started *******"+ result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("***** Test Failed But with in success percentage *******"+ result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		logger.info("***** Test Failed *******"+ result.getName());
		
	}

	public void onTestSkipped(ITestResult result) {
		logger.info("***** Test Skipped *******"+ result.getName());
		
	}

	public void onTestStart(ITestResult result) {
		logger.info("***** Test Started *******"+ result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("***** Test Successfull *******"+ result.getName());
	}
	

}
