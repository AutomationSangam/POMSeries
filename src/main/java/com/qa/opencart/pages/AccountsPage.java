package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.Constatnts;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
@Epic("Epic -200: Design Account Page")
@Story("US 201:Design the accounts page with title ,header,account sections and product results...")
public class AccountsPage extends BasePage {
	
	
	private WebDriver driver;
	private ElementUtil element;
	
	
	private By header=By.linkText("Your Store");
	private By accountSectionHeaders=By.cssSelector("div#content h2");
	private By searchBox=By.xpath("//input[@name='search']");
	private By searchBtn=By.cssSelector("div#search span button");
	private By searchItemResults=By.cssSelector("div.product-layout div.product-thumb");
	private By resultItems=By.cssSelector(".product-thumb h4 a");
	
	
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		element=new ElementUtil(this.driver);
	}
	
	@Step("Getting account page title")
	public String getAccountsPageTitle() {
		return element.waitForTitlePresent(Constatnts.ACCOUNT_PAGE_TITLE, 10);
	}
	
	
	@Step("getting account page header")
	public String getAccountsPageHeader() {
		return element.doGetText(header);
	}
	
	@Step("Getting account page section count")
	public int getAccountSectionsCount() {
		return element.getElements(accountSectionHeaders).size();
	}
	
	@Step("getting account page section list")
	public List<String>  getAccountSectionsList() {
		List<String> accountList=new ArrayList<String>();
		List<WebElement> accountSectionList= element.getElements(accountSectionHeaders);
		
		for(WebElement ele:accountSectionList) {
			System.out.println(ele.getText());
			accountList.add(ele.getText());
		}
		return accountList;
	}
	
	@Step("doing search for the product")
	public boolean doSearch(String productName) {
		element.doSendKeys(searchBox, productName);
		element.doClick(searchBtn);
		if(element.getElements(searchItemResults).size()>0) {
			return true;
		}
		return false;
	}
	
	@Step("Selet the product from list")
	public ProductInfoPage selectTheProductFromResults(String productName) {
		List<WebElement> resultItemList=element.getElements(resultItems);
		System.out.println("Total no of Item displayed:"+resultItemList.size());
		for(WebElement e:resultItemList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
