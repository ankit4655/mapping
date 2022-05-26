package com.wcm.mapping.inputDetails;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class InputDetails {
	
	public String username;
	
	public String password;
		
	public String excelTemplate;
	
	public String mappingExtract;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExcelTemplate() {
		return excelTemplate;
	}

	public void setExcelTemplate(String excelTemplate) {
		this.excelTemplate = excelTemplate;
	}

	public String getMappingExtract() {
		return mappingExtract;
	}

	public void setMappingExtract(String mappingExtract) {
		this.mappingExtract = mappingExtract;
	}

	
	

}
