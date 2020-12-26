package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CartPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	public BasePage bp;
	public Properties prop;
	public WebDriver driver;
	public LoginPage lp;
	public AccountsPage accountPage;
	public ProductInfoPage productPage;
	public CartPage cartPage;
	public RegisterPage registerpage;
	
	
	@Parameters({"browser","version"})
	@BeforeTest
	public void setup(@Optional String browserName,@Optional String browserversion) {
		 bp=new BasePage();
		prop= bp.init_prop();
		String browser=prop.getProperty("browser");
		if (browserName != null) {
			browser = browserName;
		}
		 driver=bp.init_driver(browser,browserversion);
		 String url=prop.getProperty("url");
		 lp=new LoginPage(driver);
		 driver.get(url);
	}
	
	
	
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
