package com.utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestReportManager {

	public static void extentReportGenerator() throws IOException {

		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("os", "Windows");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Ajay Kumar Verma");
		
		htmlReporter.config().setDocumentTitle("Final Execution Report");
		htmlReporter.config().setReportName("Selenium Web Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test = extent.createTest("MyFirstTest", "Sample description");
		
		// log(Status, details)
		test.log(Status.INFO, "This step shows usage of log(status, details only)");

		// info(details)
		test.info("This step shows usage of info(details)");

		// fail log with snapshot
		test.fail("failed because of some issue", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png","Screen shot attached").build());


		// calling flush writes everything to the log file
		extent.flush();

	}
	public static void main(String[] args) throws IOException {
		
		ExtentTestReportManager.extentReportGenerator();
		
		
	}
	
	
	public static ExtentReports extentRepotGeneratorNG(){

		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("os", "Windows");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Ajay Kumar Verma");

		htmlReporter.config().setDocumentTitle("Final Execution Report");
		htmlReporter.config().setReportName("Selenium Web Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);


		return extent;

	}
	
}
