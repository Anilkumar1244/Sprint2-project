package library;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

		//This method handles the excel - opens it and reads the data from the respective cells using a for-loop & returns it in the form of a string array
			public String[][] getExcelData(String fileName, String sheetName){
		      	
		      	String[][] data = null;   	
			  	try
			  	{
			   	FileInputStream fis = new FileInputStream(fileName);
			   	XSSFWorkbook wb = new XSSFWorkbook(fis);
			   	Sheet sh = wb.getSheet(sheetName);
			   	Row row = sh.getRow(0);
			   	int noOfRows = sh.getPhysicalNumberOfRows();
			   	int noOfCols = row.getLastCellNum();
			     String cell;
			   	data = new String[noOfRows-1][noOfCols];
			   	
			   	for(int i=1; i<noOfRows;i++){
				     for(int j=0;j<noOfCols;j++){
				    	 row = sh.getRow(i);
				    	   cell= row.getCell(j).getStringCellValue();
				    	   data[i-1][j] = cell;			   	 	
				    	   }
			   	}
			  	}
			  	catch (Exception e) {
			     	   System.out.println("The exception is: " +e.getMessage());
		         	}
		      	return data;
			}



	    
	}

