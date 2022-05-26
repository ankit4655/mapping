package com.wcm.mapping.testcases;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import com.wcm.mapping.base.TestBase;


public class S01_TC05_DefaultContentPathTest extends TestBase {
	
	@Test()
	public String defaultContentPathTest(String WCMPath, String userName, String password, String mappingExtract) throws IOException
	{
		fuzepageinitialization();
		driver.get(WCMPath);
		driver.findElement(By.id("userName")).sendKeys(userName);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.name("login")).click();
		String pageMP = "";
		ArrayList<String> wcmDefaultContentPathList = new ArrayList<String>();
		ArrayList<String> defaultContentPathList = new ArrayList<String>();
		FileInputStream fs2 = new FileInputStream(mappingExtract);
		XSSFWorkbook workbook = new XSSFWorkbook(fs2);
		int j=0,i,k;
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
				 if(d2.contentEquals("Page Mapping"))
				 {
					 pageMP = sheet.getRow(k).getCell(j+1).getStringCellValue();
					 //System.out.println("Page level mapping in excel file " +sheet.getRow(k).getCell(j+1).getStringCellValue());
				  }
				 if((d2.contentEquals("WCM Path")) && (sheet.getRow(k).getCell(j-1).getStringCellValue().contentEquals("WCM Path"))&&((sheet.getRow(k).getCell(j-2).getStringCellValue().contentEquals("Configuration Template"))))
				 {
					 wcmDefaultContentPathList.add(sheet.getRow(k).getCell(j+1).getStringCellValue());
				 }
				 }
				}
			   }
			  }
			}
		 }
		String updatedPagePath = pageMP.replaceFirst("/", "/Content/");
		System.out.println(updatedPagePath);
		String[] split1 = updatedPagePath.split("/");
		for(i=0;i<split1.length;i++)
		{
			System.out.println(split1[i]);
		}
		
		//List<WebElement> tablelist = driver.findElements(By.xpath("//tr//td[2]//a"));
		List<WebElement> tablelist = driver.findElements(By.xpath("//table[@class=\"lotusTable\"]//tbody[2]//tr//td//a//span"));
		System.out.println(tablelist.size());
		int counter =0;
		for(i=0;i<tablelist.size();i++)
		{
			//System.out.println(tablelist.get(i).getText());
			if(!split1[0].contentEquals(tablelist.get(i).getText()))
			{
				 counter++;
			}
		}
		System.out.println(counter);
		if(counter == tablelist.size())
		{
			driver.findElement(By.xpath("//a[@title=\"Next\"]")).click();
		}		
		for(j=0;j<split1.length;j++)
		{
		  List<WebElement> tablelist1 = driver.findElements(By.xpath("//table[@class=\"lotusTable\"]//tbody[2]//tr//td//a//span"));
		  for(i=0;i<tablelist1.size();i++)
		   {
			  if(split1[j].contentEquals(tablelist1.get(i).getText()))
			 {
				driver.findElement(By.xpath("//table[@class=\"lotusTable\"]//tbody[2]//tr["+(i+1)+"]//td//a")).click();
				break;
			 }
			 if(i == tablelist1.size())
			 {
				 j=j-1;
				 if(driver.findElement(By.xpath("//ul[@class=\"lotusLeft lotusInlinelist\"]//li[5]//a")).isEnabled())
				 {
					 driver.findElement(By.xpath("//ul[@class=\"lotusLeft lotusInlinelist\"]//li[5]//a")).click();
				 }
				 else if (driver.findElement(By.xpath("//ul[@class=\"lotusRight lotusInlinelist\"]//li[3]//a")).isEnabled())
				 {
					 driver.findElement(By.xpath("//ul[@class=\"lotusRight lotusInlinelist\"]//li[3]//a")).click();
				 }
			 }
			  
		   }
		}
		List<WebElement> breadCrumb = driver.findElements(By.xpath("//div[@id=\"wcm_breadcrumb\"]//li[@class=\"wcmBreadcrumbsElement\"]//span"));

		if(breadCrumb.get(breadCrumb.size()-1).getText().contentEquals(split1[split1.length-1]))
		{
		 List<WebElement> tablelist1 = driver.findElements(By.xpath("//table[@class=\"lotusTable\"]//tbody[2]//tr//td//a//span"));
		 for(i=0;i<tablelist1.size();i++)
		  {
			if(tablelist1.get(i).getText().contentEquals("PageConfig"))
			{
				continue;
			}

			driver.findElement(By.xpath("//table[@class=\"lotusTable\"]//tbody[2]//tr["+(i+1)+"]//td//input")).click();
			driver.findElement(By.xpath("//a[@title=\"Read\"]")).click();
			String defaultContent = driver.findElement(By.xpath("//a[@id=\"DEFAULT_CONTENT\"]//span")).getText();
            System.out.println(defaultContent.replaceAll(" ", ""));
            defaultContentPathList.add(defaultContent.replaceAll(" ", ""));
            driver.findElement(By.xpath("//a[@id=\"close_controllable\"]")).click();
            tablelist1 = driver.findElements(By.xpath("//table[@class=\"lotusTable\"]//tbody[2]//tr//td//a//span"));
			}
	    }
		else
		{
			System.out.println("Path is wrong");
		}

		List<String> excelDefaultWCMPath= wcmDefaultContentPathList;
		System.out.println(excelDefaultWCMPath.size());
		List<String> UpdatedexcelDefaultWCMPath= new ArrayList<String>();
		for(i=0;i<excelDefaultWCMPath.size();i++)
		{
			UpdatedexcelDefaultWCMPath.add(excelDefaultWCMPath.get(i).replaceAll(" ", ""));
			//System.out.println(UpdatedexcelDefaultWCMPath.get(i));
		}
		//System.out.println(UpdatedexcelDefaultWCMPath.size());
		Collections.sort(UpdatedexcelDefaultWCMPath);
		Collections.sort(defaultContentPathList);
		System.out.println(UpdatedexcelDefaultWCMPath);
		System.out.println("\n");
		System.out.println(defaultContentPathList);
		System.out.println(UpdatedexcelDefaultWCMPath);
		driver.quit();
		if(UpdatedexcelDefaultWCMPath.size() == defaultContentPathList.size() )
		{
			System.out.println(defaultContentPathList.size());
			System.out.println("SiteAreaDefaultContentPath: "+pageMP + " [DPL-CT]");
			defaultContentPathList.removeAll(defaultContentPathList);
			return "SiteAreaDefaultContentPath: "+pageMP + " [DPL-CT]";
		}
		else
		{
            System.out.println("Default Content Path is Incorrect");
			defaultContentPathList.removeAll(defaultContentPathList);
			return "Default Content Path is Incorrect";
		}
		
		
		
	}
	

}
