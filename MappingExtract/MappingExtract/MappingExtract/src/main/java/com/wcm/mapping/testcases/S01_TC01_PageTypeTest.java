package com.wcm.mapping.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import com.wcm.mapping.base.TestBase;
import com.wcm.mapping.util.TestUtil;


public class S01_TC01_PageTypeTest extends TestBase{
	public static String pageURL;
	
	@Test
	public String pageTypeTest(String mappingExtractFile, String userName, String Password)  throws IOException
	{
		FileInputStream fs2 = new FileInputStream(mappingExtractFile);
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
				if(sheet.getRow(i).getCell(j).getStringCellValue().equals("Page URL"))
				{
					pageURL = sheet.getRow(i+1).getCell(j).getStringCellValue().toString();
					break;
				}
			   }
			 }
			}
		}
		fuzepageinitialization();
		driver.get(pageURL);
		driver.findElement(By.id("userName")).sendKeys(userName);
		driver.findElement(By.id("passwd")).sendKeys(Password);
		driver.findElement(By.name("login")).click();
		driver.findElement(By.id("projectButton")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.FUZE_IMPLICIT_WAIT));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(180));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//select[@id=\"projectList\"]/optgroup[@label=\"Active Projects\"]/option[@value=\"001_VAIBHAV_TEST\"]"))));
		driver.findElement(By.xpath("//select[@id=\"projectList\"]/optgroup[@label=\"Active Projects\"]/option[@value=\"001_VAIBHAV_TEST\"]")).click();
        driver.findElement(By.xpath("//a[@id=\"utb-shelf5\"]")).click();
        List<WebElement> locales = driver.findElements(By.xpath("//ul[@dojoattachpoint=\"list\"]//li"));
        int count = locales.size();
		System.out.println(count);
		driver.quit();
		if(count>2)
		{
          System.out.println("PageType: Shared");
          return "PageType: Shared";
		}
		else
		{
			System.out.println("PageType: StandAlone");
			return "PageType: StandAlone";
		}

	}
	
}
