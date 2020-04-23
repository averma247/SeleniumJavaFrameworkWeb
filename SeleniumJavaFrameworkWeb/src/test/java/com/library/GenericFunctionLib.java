package com.library;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenericFunctionLib {


	public static String takeSnapShot(WebDriver webdriver) throws Exception{

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot =((TakesScreenshot)webdriver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination
		String filePath=System.getProperty("user.dir")+"/screenshots/"+"Screenshots_"+System.currentTimeMillis()+".png";
		File DestFile=new File(filePath);

		//Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

		return filePath;

	}


	public static void takeAlertScreenShot() throws Exception{
		
		String filePath=System.getProperty("user.dir")+"/screenshots/"+"Screenshots_"+System.currentTimeMillis()+".png";
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	    ImageIO.write(image, "png", new File(filePath));

	}



}
