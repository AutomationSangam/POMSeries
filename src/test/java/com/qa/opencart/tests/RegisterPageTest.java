package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constatnts;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	
	@BeforeClass
	public void RegisterPageSetup() {
		
		registerpage=lp.navigatetoRegisterPage();
		
		
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		
		Object data[][]=ExcelUtil.getTestData(Constatnts.TEST_DATA_SHEET_NAME);
		return data;
		
		
		
	}
	
	
	@Test(dataProvider = "getRegisterData")
	public void userregistrationTest(String firstname,String lastname,String emailid,String telephone,String password,String subscribe) {
		registerpage.accountRegistration(firstname,lastname,emailid,telephone,password,subscribe);
	}

}
