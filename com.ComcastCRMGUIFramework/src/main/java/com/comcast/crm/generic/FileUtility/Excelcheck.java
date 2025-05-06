package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelcheck {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sheetName = wb.getSheet("org");
		int rowCount = sheetName.getPhysicalNumberOfRows();			// It gives number not index
	//	System.out.println("Rows: " + rowCount);
		
		for(int i = 0; i < rowCount; i++)
		{
			String data = "";
			try 
			{
				Row rowNum = sheetName.getRow(i);
				int cellCount = rowNum.getPhysicalNumberOfCells();			// It gives number not index
			//	System.out.println("Cells: " + cellCount);
				for(int j = 0; j < cellCount; j++)
				{
					String cell = rowNum.getCell(j).toString();
					data += cell + "\t";
				}
				System.out.println(data);
			}
			catch (Exception e) 
			{
				System.out.println(data);
			}
		}
		
		wb.close();
	}

}
