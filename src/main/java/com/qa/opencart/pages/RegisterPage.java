package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.Constatnts;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage extends BasePage{
	private ElementUtil elementutil;
	
	private By firstName=By.cssSelector("#input-firstname");
	private By lastName=By.cssSelector("#input-lastname");
	private By emailid=By.cssSelector("#input-email");
	private By telephone=By.cssSelector("#input-telephone");
	private By password=By.cssSelector("#input-password");
	private By confirmpassword=By.cssSelector("#input-confirm");
	private By subscribewithyes=By.xpath("//input[@name='newsletter' and @value='1']");
	private By subscribewithno=By.xpath("//input[@name='newsletter' and @value='0']");
	private By agreecheckbox=By.xpath("//input[@name='agree']");
	private By continuebtn=By.xpath("//input[@value='Continue']");
	private By accountSuccessMsg=By.cssSelector("#content h1");
	private By logout=By.linkText("Logout");
	private By registerLink=By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
		
		
	}
	
	
	
	public boolean accountRegistration(String fname,String lname,String email,String telephone,String password,String subscribe) {
		
		elementutil.doSendKeys(firstName, fname);
		elementutil.doSendKeys(lastName, lname);
		elementutil.doSendKeys(emailid, email);
		elementutil.doSendKeys(this.telephone, telephone);
		elementutil.doSendKeys(this.password, password);
		elementutil.doSendKeys(confirmpassword, password);
		if(subscribe.equals("yes")) {
			elementutil.doClick(subscribewithyes);
		}else {
				elementutil.doClick(subscribewithno);
		}
		elementutil.doClick(agreecheckbox);
		elementutil.doClick(continuebtn);
		String text=elementutil.doGetText(accountSuccessMsg);
		if(text.contains(Constatnts.ACCOUNT_CREATION_MESSAGE)) {
			elementutil.doClick(logout);
			elementutil.doClick(registerLink);
			return true;
		}else {
			return false;
		}
		
	}
	
}
