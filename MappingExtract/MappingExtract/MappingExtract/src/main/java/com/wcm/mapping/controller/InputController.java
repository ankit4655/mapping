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
import com.wcm.mapping.testcases.S01_TC01_PageTypeTest;
import com.wcm.mapping.testcases.S01_TC02_PageLevelMappingTest;
import com.wcm.mapping.testcases.S01_TC03_PortletMappingTest;
import com.wcm.mapping.testcases.S01_TC04_ContextOverrideTest;
import com.wcm.mapping.testcases.S01_TC05_DefaultContentPathTest;
import com.wcm.mapping.testcases.S01_TC06_PageUniqueNameTest;

@Controller
@RequestMapping("/standalone")
public class InputController {

	@RequestMapping("/xmlimport")
	public String listCustomers(Model theModel) {
		theModel.addAttribute("inputDetails", new InputDetails());
		return "xml-import";
	}
	
	@RequestMapping("/viewresults")
	public String saveDetails(@ModelAttribute("inputDetails") InputDetails theinputDetails,
			BindingResult theBindingResult, Model theModel) throws IOException {
		
		String WCMPath = "https://fuzeauth.3m.com/wps/myportal/Applications/Content/Authoring/";
		
		String op1 = "";
		
		List<String> outPut = new ArrayList<>();
		
		S01_TC01_PageTypeTest thepageTypeTest = new S01_TC01_PageTypeTest();
		
		S01_TC02_PageLevelMappingTest thePageLevelMappingTest = new S01_TC02_PageLevelMappingTest();
		
		S01_TC03_PortletMappingTest thePortletMappingTest = new S01_TC03_PortletMappingTest();
		
		S01_TC04_ContextOverrideTest theContextOverrideTest = new S01_TC04_ContextOverrideTest();
		
		S01_TC05_DefaultContentPathTest theDefaultContentPathTest = new S01_TC05_DefaultContentPathTest();
		
		S01_TC06_PageUniqueNameTest thePageUniqueTest = new S01_TC06_PageUniqueNameTest();
		
		try {
		
		op1 = thepageTypeTest.pageTypeTest(theinputDetails.getMappingExtract(), theinputDetails.getUsername(), theinputDetails.getPassword());
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		String op2 = thePageLevelMappingTest.pageLevelMappingTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract());
		
    	String op3 =  thePortletMappingTest.portletMappingTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract());
		
     	String op4 = theContextOverrideTest.contextOverrideTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract());
		
     	String op5 = theDefaultContentPathTest.defaultContentPathTest(WCMPath, theinputDetails.getUsername(), theinputDetails.getPassword(), theinputDetails.getMappingExtract());
	
		String op6 = thePageUniqueTest.pageUniqueNameTest(theinputDetails.getExcelTemplate(), theinputDetails.getMappingExtract());
		
		outPut.add(op1);
		outPut.add(op2);
		outPut.add(op3);
		outPut.add(op4);
		outPut.add(op5);
		outPut.add(op6);
		
//		System.out.println(theinputDetails.getExcelTemplate());
		
		
//		System.out.println("Output: "+outPut.get(0));
		 
		theModel.addAttribute("output", outPut);
		
		return "view-result";
		
	}
	
	
}
