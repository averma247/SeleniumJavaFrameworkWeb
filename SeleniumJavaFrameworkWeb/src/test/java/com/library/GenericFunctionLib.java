package com.library;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.listeners.TestNGListeners;
import com.utils.ConfigGenerator;
import com.utils.ObjectRepositoryGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericFunctionLib {
	private static	Logger logger = LogManager.getLogger(GenericFunctionLib.class);
	public static Properties configprop=null;
	public static Properties objRepository=null;
	public static String browser=null;
	public static WebDriver driver = null;

	public WebDriver initializeDriver(){
		
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
		
		return driver;

		
	}

	public static String getScreenShotPath(String TestCaseName, WebDriver driver) throws Exception{

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot =((TakesScreenshot)driver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination
		String filePath=System.getProperty("user.dir")+"/screenshots/"+TestCaseName+"_"+System.currentTimeMillis()+".png";
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
