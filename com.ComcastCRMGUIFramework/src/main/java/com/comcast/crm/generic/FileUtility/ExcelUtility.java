package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/** This class consists of method related to Excel File
 * @author Amit
 */
public class ExcelUtility
{
	/**
	 * This method is used to read Data of a Cell from Excel File provided sheet, row, cell
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException
	 */
	public String getDataFromExcelFile(String sheet, int row, int cell) throws EncryptedDocumentException, IOException  
	{
		FileInputStream fis = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String cellData = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		wb.close();
		
		return cellData;
	}
	
	/**
	 * This method is used to read Data of entire Row from Excel File provided sheet, row
	 * @param sheet
	 * @param row
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException
	 */
	public String getEntrireDataFromExcelFile(String sheet) throws EncryptedDocumentException, IOException 
	{
		String data = "";
		
		FileInputStream fis = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sheetName = wb.getSheet(sheet);
		int rowCount = sheetName.getLastRowNum();
		for(int i = 0; i <= rowCount; i++)
		{
			Row rowNum = sheetName.getRow(i);
			short cellCount = rowNum.getLastCellNum();
			for(int j = 0; j <= cellCount; j++)
			{
				String cell = rowNum.getCell(j).toString();
				data += cell;
			}
			System.out.println("\n");
		}
		wb.close();
		
		return data;
	}
	
	/**
	 * This method is used Count number of Rows in a Sheet of Excel File provided sheet
	 * @param sheet
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException
	 */
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		
		return rowCount;
	}
	
	
	public int getCellCount(String sheet, int i) throws EncryptedDocumentException, IOException 
	{
		int cellCount = 0;
		
		FileInputStream fis = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheet).getLastRowNum();
		for(i = 0; i < rowCount; i++)
			cellCount = wb.getSheet(sheet).getRow(i).getLastCellNum();

		wb.close();
		
		return cellCount;
	}
	
	/**
	 * This method is used to write Data into a Cell of Excel File provided sheet, row, cell, data
	 * @param sheet
	 * @param row
	 * @param cell
	 * @param data
	 * @throws IOException 
	 * @throws EncryptedDocumentException
	 */
	public void setDataIntoExcelFile(String sheet, int row, int cell, String data) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheet).getRow(row).createCell(cell, CellType.STRING).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./testData/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();	//Mandatory because Excel Resource side, object remain open which can crash the file
					// Java side after execution object get garbage collected.
		
	}
}





