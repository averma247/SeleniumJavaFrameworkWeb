package com.utils;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.listeners.TestNGListeners.class)
public class ExcelDataProvider {
	private static	Logger logger = LogManager.getLogger(ExcelDataProvider.class);
	
	
	@Test(dataProvider="test1Data")
	public void sampleTest(HashMap<Object,Object>data){
		
		logger.info(data.get("UserName").toString()+" | "+ 	data.get("Password").toString());
		
	}
	
	@Test(retryAnalyzer = com.listeners.RetryAnalyzerListener.class)
	public void sampleTest1(){
		
		logger.info("Sample Test 1");
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void sampleTest2(){
		
		logger.info("Sample Test 2");
		//throw new SkipException("Skipping this exception");
		
	}
	
	@DataProvider(name="test1Data")
	public Object[][] getTestData(){
		
		String projectPath= System.getProperty("user.dir");
		String excelPath=projectPath+"/databank/data.xlsx";		
		Object data[][]=testData(excelPath, "Sheet1");
		return data;
	}
	
	
	public static Object[][] testData(String excelPath, String sheetName){

		ExcelUtils excelData= new ExcelUtils(excelPath, sheetName);
		int lastRowCount=excelData.getLastRowCount();
		int lastCellCount=excelData.getLastColCount();
		Object[][]testData=new Object[lastRowCount][1];

		logger.info("Reading Cell Data");
		for(int i=0; i<lastRowCount;i++){
			HashMap<Object, Object> datamap = new HashMap<Object, Object>();
			for(int j=0;j<lastCellCount;j++){
				datamap.put(excelData.getCellData(0,j),excelData.getCellData(i+1,j));
				logger.info(" Test data values available: "+ Arrays.asList(datamap));
			}
			testData[i][0] = datamap;
		}
		
		return testData;
	}

}
