package com.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static	Logger logger = LogManager.getLogger(ExcelUtils.class);
	static 	XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName){
		logger.info("Reading from Excel file at location: "+ excelPath);
		try {

			workbook = new XSSFWorkbook(excelPath);
			logger.info("Excel file read succesfully.");
			sheet = workbook.getSheet(sheetName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getLastRowCount(){
		int rowCount=0;
		try {			
			rowCount=sheet.getLastRowNum();
			logger.debug("Row Count Value is: "+ rowCount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ErrorMessage: "+e.getMessage());
			logger.error("Reason for Error: ", e.getCause());
		}			
		return rowCount;
	}

	public String getCellDataString(int rowNum, int colNum){
		String cellData=null;
		try {

			cellData=sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			//System.out.println("CellData: "+ cellData);
			//logger.info("CellData: "+ cellData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ErrorMessage: "+e.getMessage());
			logger.error("Reason for Error: ", e.getCause());
		}			
		return cellData;
	}
	
	public String getCellData(int rowNum, int colNum){
		String cellData=null;
		try {

			cellData=sheet.getRow(rowNum).getCell(colNum).toString();
			//System.out.println("CellData: "+ cellData);
			//logger.info("CellData: "+ cellData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ErrorMessage: "+e.getMessage());
			logger.error("Reason for Error: ", e.getCause());
		}			
		return cellData;
	}
	
	public void getCellDataNumber(int rowNum, int colNum){

		try {

			double cellData=sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println("CellData: "+ cellData);
			logger.info("CellData: "+ cellData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ErrorMessage: "+e.getMessage());
			logger.error("Reason for Error: ", e.getCause());
		}			

	}


	public int getLastColCount(){

		int colCount=0;
		try {

			colCount=sheet.getRow(0).getLastCellNum();
			logger.debug("Last Column Count Value is: "+ colCount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ErrorMessage: "+e.getMessage());
			logger.error("Reason for Error: ", e.getCause());
		}			
		return colCount;
	}




	public static void main(String[] args) {

		//getRowCount();
		String projectPath= System.getProperty("user.dir");
		ExcelUtils excel=new ExcelUtils(projectPath+"/databank/data.xlsx",	"Sheet1");
		excel.getCellDataString(1,1);
	}

}
