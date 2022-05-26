package com.wcm.mapping.sharedtestcases;

import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.wcm.mapping.base.TestBase;


public class S01_TC06_PageUniqueNameTest extends TestBase{
	
	@Test
	public String pageUniqueNameTest(String mappingExtract) throws IOException
	{
		String excelPUN = "";
		FileInputStream fs3 = new FileInputStream(mappingExtract);
		XSSFWorkbook workbook1 = new XSSFWorkbook(fs3);
		int j=0,i,k;
		XSSFSheet sheet1 = workbook1.getSheetAt(1);
		for(i=0;i<sheet1.getLastRowNum();i++)
		{
			Cell data1 = sheet1.getRow(i).getCell(0);
			if(data1 != null)
			{
			 String d1 = data1.getStringCellValue();
			 if(d1.contains("Page"))
			 {
			  Row row = sheet1.getRow(i);
			  for(j=0;j<row.getLastCellNum();j++)
			   {
				if(sheet1.getRow(i).getCell(j).getStringCellValue().equals("Page Unique Name"))
				{
					excelPUN = sheet1.getRow(i+1).getCell(j).getStringCellValue().toString();
					break;
				}
			   }
			 }
			}
		}
		System.out.println(excelPUN);
	  return "PageUniqueName: "+ excelPUN;
	}

}
