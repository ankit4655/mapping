package com.wcm.mapping.testcases;


import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.wcm.mapping.base.TestBase;



public class S01_TC02_PageLevelMappingTest extends TestBase{
	
	
	@Test
	public String pageLevelMappingTest(String excelTemplate, String mappingExtract) throws IOException
	{
		String excelPageLevelMp = null;
//		FileInputStream fs2 = fileReader(excelTemplate);
		FileInputStream fs2 = new FileInputStream(excelTemplate);
		XSSFWorkbook workbook = new XSSFWorkbook(fs2);
		XSSFSheet sheet = workbook.getSheetAt(0);
	    XSSFCell col1 = workbook.getSheetAt(0).getRow(1).getCell(0);
	    String sourcePath = col1.getStringCellValue();
	    String finalSourcePath = sourcePath.replaceAll(" ", "");
	    XSSFCell col2 = workbook.getSheetAt(0).getRow(1).getCell(1);
	    String targetPath  = col2.getStringCellValue();
	    if(targetPath.charAt(targetPath.length()-1)=='/')
	    {
	    	targetPath = targetPath.substring(0,targetPath.length()-1);
	    }
	    String finaltargetPath = targetPath.replaceAll(" ", "");
	    String[] sourcePathSplit = finalSourcePath.split("/");
	    String siteAreaName = sourcePathSplit[sourcePathSplit.length-1];
		String fuzePageLevelMp = finaltargetPath.concat("/").concat(siteAreaName);
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
				if(sheet1.getRow(i).getCell(j).getStringCellValue().equals("Field Label"))
				{
				int rowCount = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
				for (k = i; k < rowCount+1; k++) 
				 {
				 Cell data2 = sheet1.getRow(k).getCell(j);
				 String d2 = data2.getStringCellValue();
				 if(d2.contentEquals("Page Mapping"))
				 {
					 excelPageLevelMp = sheet1.getRow(k).getCell(j+1).getStringCellValue();
					// System.out.println("Page level mapping in excel file " +sheet1.getRow(k).getCell(j+1).getStringCellValue());
				  }
				 }
				}
			   }
			  }
			}
		 }		
		
		String finalExcelPageLevelMp = excelPageLevelMp.replaceAll(" ", "");
		System.out.println(finalExcelPageLevelMp);
		System.out.println(fuzePageLevelMp);
		if(fuzePageLevelMp.equalsIgnoreCase(finalExcelPageLevelMp))
		{
            System.out.println("Page Level Mapping: "+fuzePageLevelMp);
            return "Page Level Mapping: " + fuzePageLevelMp;
           
		}
		else
		{
			System.out.println("Page Level Mapping is incorrect");
			return "Page Level Mapping is incorrect";

		}
	}

}
