package com.wcm.mapping.sharedtestcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wcm.mapping.base.TestBase;


public class S01_TC03_PortletMappingTest extends TestBase {

	
	@Test
	public String portletMappingTest(String excelTemplate , String mappingExtract, String defaultpath) throws IOException
	{
		int m = 0;
		FileInputStream fs2 = new FileInputStream(mappingExtract);
		XSSFWorkbook workbook = new XSSFWorkbook(fs2);
		int j=0,i,k;
		ArrayList<String> portletMappingList = new ArrayList<String>();
		XSSFSheet sheet = workbook.getSheetAt(1);
		for(i=0;i<sheet.getLastRowNum();i++)
		{
			Cell data1 = sheet.getRow(i).getCell(0);
			if(data1 != null)
			{
			 String d1 = data1.getStringCellValue();
			 if(d1.contains("Page"))
			 {
			  Row row = sheet.getRow(i);
			  for(j=0;j<row.getLastCellNum();j++)
			   {
				if(sheet.getRow(i).getCell(j).getStringCellValue().equals("Field Label"))
				{
				int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
				for (k = i; k < rowCount+1; k++) 
				 {
				 Cell data2 = sheet.getRow(k).getCell(j);
				 String d2 = data2.getStringCellValue();
				 if(d2.contentEquals("Portlet Mapping")) 
				 {
					 portletMappingList.add(sheet.getRow(k).getCell(j+1).getStringCellValue());
					 
				 }
				 }
				}
			   }
			  }
			}
		 }
		//System.out.println("Portlet Mapping List "+portletMappingList);
//		FileInputStream fs3 = new FileInputStream(excelTemplate);
//		XSSFWorkbook workbook1 = new XSSFWorkbook(fs3);
//		XSSFSheet sheet1 = workbook1.getSheetAt(0);
//	    XSSFCell col1 = workbook1.getSheetAt(0).getRow(1).getCell(0);
//	    String sourcePath = col1.getStringCellValue();
//	    String finalSourcePath = sourcePath.replaceAll(" ", "");
		//System.out.println("Fuze Page Level Mapping "+fuzePageLevelMp);
		Set<String> set1 = new HashSet<String>();
		ArrayList<String> list1 = new ArrayList<String>();
		for(i=0; i<portletMappingList.size();i++)
		{
			//String s1 = portletMappingList.get(i).replaceAll(" ", "");
			String s1 = portletMappingList.get(i);
			//System.out.println(s1);
			String[] s2 = s1.split("/");
			String s3 = s2[s2.length-1];
			if(s1.substring(0, (s1.length()-(s3.length()+1))).equalsIgnoreCase(defaultpath))
			{
			if(s3.substring(0, 2).contains("rG") ||s3.substring(0, 2).contains("rH") || s3.substring(0, 2).contains("rN") || s3.substring(0, 2).contains("rC") || s3.substring(0, 2).contains("rU") || s3.substring(0, 2).contains("rW") || s3.substring(0, 4).contains("rSEL") || s3.substring(0, 4).contains("rTBL") || s3.substring(0, 2).contains("rg") )
			{
				list1.add(s3);
				
			}
		   }
		}
		 //System.out.println("PortletMapping "+list1.size());
		 for(String s4 : list1)
		 {
	         if(set1.add(s4) == false)
	           {
	               System.out.println(s4 + " is duplicated");
	               m=1;
	           }  
	     }
		 if(portletMappingList.size() == set1.size())
		 {

		    System.out.println("Portlet Mapping: "+defaultpath.toUpperCase()+ " [DPL-SiteArea]");
            return "Portlet Mapping: "+ defaultpath.toUpperCase() +" [DPL-SiteArea]";
		 }
		 else
		 {
			System.out.println("Portlet Mapping is incorrect");
            return "Portlet Mapping is incorrect";
		 }
	}

}
