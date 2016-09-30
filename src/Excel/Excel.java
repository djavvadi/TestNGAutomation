package Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;



public class Excel {
	
	private String excelPath;
	
public void readExcelPath(){
	File file = new File("");
	excelPath = file.getAbsolutePath()+"\\Datatables\\Datatable.xls";
	
}
private HSSFWorkbook excelWorkBook() throws FileNotFoundException,IOException{
	
	readExcelPath();
	File file =    new File(excelPath);
	
	FileInputStream inputStream = new FileInputStream(file);
	
	HSSFWorkbook TestData = new HSSFWorkbook(inputStream);
				
	return TestData;
	
}

public String getData(String TestName, String ColName){
	
	HSSFWorkbook TestData;
	String data = "";
	try {
		TestData = excelWorkBook();
		HSSFSheet testdataSheet = TestData.getSheet("GeneralTestData");
	
			int rowNum;
			int colNum;
			try {
				colNum = getColNum(testdataSheet,ColName);
				rowNum = getRowNum(testdataSheet,TestName);
				data = getCellValue(testdataSheet,rowNum,colNum);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return data;
	
}


public void putData(String TestName, String ColName, String Value){
	
	HSSFWorkbook TestData;

	try {
		TestData = excelWorkBook();
		HSSFSheet testdataSheet = TestData.getSheet("GeneralTestData");
	
			int rowNum;
			int colNum;
			try {
				colNum = getColNum(testdataSheet,ColName);
				rowNum = getRowNum(testdataSheet,TestName);
				setValue(TestData, testdataSheet,rowNum,colNum,Value);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

private int getRowNum(HSSFSheet testdataSheet, String TestName) throws FileNotFoundException, IOException{
	
	int rowCount = testdataSheet.getLastRowNum() - testdataSheet.getFirstRowNum();
	int rowNum = 0;
	for(int i=0;i<rowCount+1;i++){
		Row row = testdataSheet.getRow(i);
		 for (int j = 0; j < row.getLastCellNum(); j++) {
			 if(row.getCell(j).getStringCellValue().equals(TestName)){
				 rowNum = i;
				 break;
			 }
			
		 }
		

		 
	}
	
	return rowNum;
	
}


private int getColNum(HSSFSheet testdataSheet,String ColName) throws FileNotFoundException, IOException{
	
	int rowCount = testdataSheet.getLastRowNum() - testdataSheet.getFirstRowNum();
	int rowNum = 0;
	for(int i=0;i<rowCount+1;i++){
		Row row = testdataSheet.getRow(i);
		 for (int j = 0; j < row.getLastCellNum(); j++) {
			 if(row.getCell(j).getStringCellValue().equals(ColName)){
				 rowNum = j;
				 break;
			 }
			
		 }
		

		 
	}
	
	return rowNum;
	
}


private String getCellValue(HSSFSheet worksheet, int rowNum, int columnNum)
{
	HSSFRow row = worksheet.getRow(rowNum);
	HSSFCell cell = row.getCell(columnNum);


	String cellValue = null;

	if (cell == null)
		cellValue = "";
	/*     */     	else {
		/* 133 */       
		switch (cell.getCellType())
		{
		case Cell.CELL_TYPE_BLANK: 
			cellValue  = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:						
			//cellValue = Double.toString(cell.getNumericCellValue());
			DataFormatter formatter = new DataFormatter();
			cellValue = formatter.formatCellValue(cell);
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue().trim();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = Boolean.toString(cell.getBooleanCellValue());	
			break;
		}

	/*     */     }
	/* 135 */     return cellValue;
}


public void setValue(HSSFWorkbook TestData,HSSFSheet worksheet,int rowNum, int columnNum, String value){

	
	HSSFRow row = worksheet.getRow(rowNum);
	HSSFCell cell = row.createCell(columnNum);
	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	cell.setCellValue(value);

	writeIntoFile(TestData);
}

private void writeIntoFile(HSSFWorkbook workbook)
{
	String absoluteFilePath = excelPath;

	FileOutputStream fileOutputStream;
	try
	{
		fileOutputStream = new FileOutputStream(absoluteFilePath);
		workbook.write(fileOutputStream);
		fileOutputStream.close();
	}
	catch (FileNotFoundException e)
	{
		e.printStackTrace();
	}

	 catch (IOException e) {
		e.printStackTrace();
	}
}
}
