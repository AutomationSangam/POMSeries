package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.Constatnts;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
@Epic("Epic 100:define login page features")
@Story("US 101:Define the loginpage class feature with title,header and forgot password link")
public class LoginPage extends BasePage{
	
	private WebDriver driver;
	private ElementUtil ele;
	//1.By Locators--Object Repositories
	
	private By loginHeader=By.xpath("//h2[contains(text(),'Returning Customer')]");
	private By textMsg=By.xpath("//strong[contains(text(),'returning customer')]");
	private By emailText=By.xpath("//label[contains(text(),'E-Mail Address')]");
	private By passwordText=By.xpath("//label[contains(text(),'Password')]");
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By forgotPasswordLink=By.linkText("Forgotten Password123");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By registerLink=By.linkText("Register");
	
	//2.Constructor of the page class
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(this.driver);
	}
	
	
	//3.Page Actions 
	@Step("Getting the login page title...")
	public String getLoginPageTitle() {
		return ele.waitForTitlePresent(Constatnts.LOGIN_PAGE_TITLE, 10);
	}
	
	public String getLoginPageHeader() {
		return ele.doGetText(loginHeader);
	}
	public String getTextMsg() {
		return ele.doGetText(textMsg);
	}
	@Step("verifying whether email text exist or not")
	public boolean isEmailTextExist() {
		return ele.doIsDisplayed(emailText);
	}
	public boolean isPwdTextExist() {
		return ele.doIsDisplayed(passwordText);
	}
	@Step("checking forgot password link")
	public boolean isForgotPwdLinkExist() {
		return ele.doIsDisplayed(forgotPasswordLink);
	}
	
	@Step("login with username:{0} and password:{1} ")
	public AccountsPage doLogin(String un,String pwd) {
		System.out.println("Login with un"+un+" and password: "+pwd);
		ele.doSendKeys(emailId, un);
		ele.doSendKeys(password, pwd);
		ele.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to register page")
	public RegisterPage navigatetoRegisterPage() {
		ele.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	

}
