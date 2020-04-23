package com.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryAnalyzerListener implements IRetryAnalyzer,IAnnotationTransformer  {
	private static	Logger logger = LogManager.getLogger(RetryAnalyzerListener.class);

	private int retryCount = 0;
	private static final int maxRetryCount = 3;
	public boolean retry(ITestResult result) {
		logger.debug("--- Executing Failed test case again ----");
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		logger.debug("--- Exiting Retry ----");
		return false;
	}
	
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

		if (retry == null)	{
			testannotation.setRetryAnalyzer(RetryAnalyzerListener.class);
		}
	}
}
