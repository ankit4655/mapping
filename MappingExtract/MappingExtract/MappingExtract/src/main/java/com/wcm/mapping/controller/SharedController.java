package com.wcm.mapping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wcm.mapping.inputDetails.InputDetails;
import com.wcm.mapping.sharedtestcases.S01_TC02_PageLevelMappingTest;
import com.wcm.mapping.sharedtestcases.S01_TC03_PortletMappingTest;
import com.wcm.mapping.sharedtestcases.S01_TC04_ContextOverrideTest;
import com.wcm.mapping.sharedtestcases.S01_TC05_DefaultContentPathTest;
import com.wcm.mapping.sharedtestcases.S01_TC06_PageUniqueNameTest;
import com.wcm.mapping.sharedtestcases.S01_TC01_PageTypeTest;

@Controller
@RequestMapping("/shared")
public class SharedController {
	
	@RequestMapping("/contentOnlyImport")
	public String listCustomers(Model theModel) {
		theModel.addAttribute("inputDetails", new InputDetails());
		return "contentonly-import";
	}
	
	@RequestMapping("/viewresults")
	public String saveDetails(@ModelAttribute("inputDetails") InputDetails theinputDetails,
			BindingResult theBindingResult, Model theModel) throws IOException {
		
		String WCMPath = "https://fuzeauth.3m.com/wps/myportal/Applications/Content/Authoring/";
		
		List<String> outPut = new ArrayList<>();
		
		String op1 =null, op2 = null, op3 = null;
		
		S01_TC01_PageTypeTest thepageTypeTest = new S01_TC01_PageTypeTest();
		
        S01_TC02_PageLevelMappingTest thePageLevelMappingTest = new S01_TC02_PageLevelMappingTest();
		
		S01_TC03_PortletMappingTest thePortletMappingTest = new S01_TC03_PortletMappingTest();
		
		S01_TC04_ContextOverrideTest theContextOverrideTest = new S01_TC04_ContextOverrideTest();
		
//		S01_TC05_DefaultContentPathTest theDefaultContentPathTest = new S01_TC05_DefaultContentPathTest();
		
		S01_TC06_PageUniqueNameTest thePageUniqueTest = new S01_TC06_PageUniqueNameTest();
		
		try {
			
		  op1 = thepageTypeTest.pageTypeTest(theinputDetails.getMappingExtract(), theinputDetails.getUsername(), theinputDetails.getPassword());
		
		  op2 = thePageLevelMappingTest.pageLevelMappingTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract(), op1);
		  
		  op3 =  thePortletMappingTest.portletMappingTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract(), op1);
		  
		}
		catch (Exception exc) {
			System.out.println(exc);
		}
		
//		String op2 = thePageLevelMappingTest.pageLevelMappingTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract(), op1);
		
    	
		
     	String op4 = theContextOverrideTest.contextOverrideTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract());
		
 //   	String op5 = theDefaultContentPathTest.defaultContentPathTest(WCMPath, theinputDetails.getUsername(), theinputDetails.getPassword(), theinputDetails.getMappingExtract(), theinputDetails.getExcelTemplate());
	
		String op6 = thePageUniqueTest.pageUniqueNameTest(theinputDetails.getMappingExtract());
		
		outPut.add("Page Type ");
		outPut.add(op2);
		outPut.add(op3);
		outPut.add(op4);
//		outPut.add(op5);
		outPut.add(op6);
		
		theModel.addAttribute("output", outPut);
		
		return "view-result";
		
	}
	

}
