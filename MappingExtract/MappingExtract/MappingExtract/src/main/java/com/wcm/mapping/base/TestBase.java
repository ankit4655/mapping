package com.wcm.mapping.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wcm.mapping.util.TestUtil;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static ArrayList<String> outPutList = new ArrayList<String>();
	
	
//	public TestBase() {
//		try {
//			prop = new Properties();
//			FileInputStream ip = new FileInputStream("C:\\Users\\SR20094451\\eclipse-workspace\\FrontEnd\\JSPProject\\src\\com\\wcm\\qa\\config\\config.properties");
//			prop.load(ip);
//			FileWriter myWriter = new FileWriter("C:\\Users\\SR20094451\\eclipse-workspace\\FrontEnd\\JSPProject\\OutPut.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	public static void initialization(){
//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\SR20094451\\Downloads\\chromedriver_win32\\chromedriver.exe");	
//	driver = new ChromeDriver();
//	driver.manage().window().maximize();
//	driver.manage().deleteAllCookies();
//	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
//	driver.get(prop.getProperty("url"));
//	}
	
	public static void fuzepageinitialization(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\a702hzz\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");	
		//System.out.println(filepath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.FUZE_PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.FUZE_IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
	
//	public static FileInputStream fileReader(String in) throws IOException {
//		try {
//			prop = new Properties();
////			FileInputStream ip = new FileInputStream();
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		String inputFile = prop.getProperty(in);
//		System.out.println(in);
//		FileInputStream fs1 = new FileInputStream(in);
//		return fs1;
//	}

}
