package com.wcm.mapping.testcases;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.wcm.mapping.base.TestBase;





public class S01_TC04_ContextOverrideTest extends TestBase {
	
	
	@Test
	public String contextOverrideTest(String excelTemplate, String mappingExtract) throws IOException
	{	

		int j=0,i,m = 0,l=0;
		FileInputStream fs2 = new FileInputStream(mappingExtract);
		XSSFWorkbook workbook = new XSSFWorkbook(fs2);
		ArrayList<String> contextOverrideList = new ArrayList<String>();
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
				for (int k = i; k < rowCount+1; k++) 
				 {
				 Cell data2 = sheet.getRow(k).getCell(j);
				 String d2 = data2.getStringCellValue();
				 if((d2.contentEquals("DocumentPath")) && (sheet.getRow(k).getCell(j-1).getStringCellValue().contentEquals("Context Override"))&&((sheet.getRow(k).getCell(j-2).getStringCellValue().contentEquals("Configuration Template"))))
				 {
					 contextOverrideList.add(sheet.getRow(k).getCell(j+1).getStringCellValue());
				 }
				 }
				}
			   }
			  }
			}
		 }
//		System.out.println("Context Override List "+contextOverrideList.size());
		FileInputStream fs3 = new FileInputStream(excelTemplate);
		XSSFWorkbook workbook1 = new XSSFWorkbook(fs3);
		XSSFSheet sheet1 = workbook1.getSheetAt(0);
	    XSSFCell col1 = workbook1.getSheetAt(0).getRow(1).getCell(0);
	    String sourcePath = col1.getStringCellValue();
	    String finalSourcePath = sourcePath.replaceAll(" ", "");
	    XSSFCell col2 = workbook1.getSheetAt(0).getRow(1).getCell(1);
	    String targetPath  = col2.getStringCellValue();
	    if(targetPath.charAt(targetPath.length()-1)=='/')
	    {
	    	targetPath = targetPath.substring(0,targetPath.length()-1);
	    }
	    String finaltargetPath = targetPath.replaceAll(" ", "");
	    String[] sourcePathSplit = finalSourcePath.split("/");
	    String siteAreaName = sourcePathSplit[sourcePathSplit.length-1];
		String fuzePageLevelMp = finaltargetPath.concat("/").concat(siteAreaName);
		Set<String> set1 = new HashSet<String>();
		ArrayList<String> list1 = new ArrayList<String>();
		for(i=0; i<contextOverrideList.size();i++)
		{
			String s1 = contextOverrideList.get(i).replaceAll(" ", "");
			String[] s2 = s1.split("/");
			String s3 = s2[s2.length-1];
			String s4 = s2[s2.length-2];
//			System.out.println(s4);
			if(s4.substring(0,2).contains("rC"))
			{
				l++;
//				System.out.println(l);
			}
			if(s1.substring(0, (s1.length()-(s3.length()+1))).equalsIgnoreCase(fuzePageLevelMp))
			{
			if(s3.substring(0, 2).contains("rG") ||s3.substring(0, 2).contains("rH") || s3.substring(0, 2).contains("rN") || s3.substring(0, 2).contains("rC") || s3.substring(0, 2).contains("rU") || s3.substring(0, 2).contains("rW") || s3.substring(0, 4).contains("rSEL") || s3.substring(0, 4).contains("rTBL"))
			{
				list1.add(s3);
			}
			}
		}
//		System.out.println(list1.size());
//		System.out.println(l);
		 for(String s4 : list1)
		 {
	         if(set1.add(s4) == false)
	           {
	               System.out.println(s4 + " is duplicated");
	               m=1;
	           }  
	     }
//		 System.out.println(set1.size());
//		 Assert.assertEquals(set1.size(),contextOverride.size()-k);
//		 Assert.assertEquals(m,0);
		 if(set1.size() == contextOverrideList.size()-l)
		 {
			 System.out.println("Context Override: "+fuzePageLevelMp + " [DPL-SiteArea]");
			 return "Context Override: "+fuzePageLevelMp + " [DPL-SiteArea]";
		 }
		 else
		 {
			 System.out.println("Context Override is incorrect");
			 return "Context Override is incorrect";
		 }
	}

}
