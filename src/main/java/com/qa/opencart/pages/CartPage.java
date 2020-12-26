package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class CartPage extends BasePage{
	
	private WebDriver driver;
	private ElementUtil elementutil;
	private JavaScriptUtil jsutil;
	
	private By macBookProInfo=By.xpath("//div[@class='table-responsive']//a[contains(text(),'MacBook Pro')]/../following-sibling::td");
	private By cartPageHeader=By.cssSelector("#content h1");
	private By nextActionsHeader=By.cssSelector("h2");
	private By preCheckoutAction=By.cssSelector("div #accordion a");
	private By estimateShippingToggle=By.cssSelector("div #accordion a[href='#collapse-shipping']");
	private By selectCountry=By.cssSelector("#input-country option");
	private By maryLandText=By.xpath("//select[@id='input-zone']/option[contains(text(),'Maryland')]");
	private By selectState=By.cssSelector("#input-zone option");
	private By postCode=By.cssSelector("#input-postcode");
	private By getQuotesButton=By.cssSelector("div #button-quote");
	private By flatRateButton=By.xpath("//input[@name='shipping_method']");
	private By applyShippingButton=By.cssSelector("input#button-shipping");
	private By successText=By.cssSelector(".alert-success");
	private By flatShippingText=By.xpath("(//strong[contains(text(),'Flat Shipping')])[2]");
	
	public CartPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
		jsutil=new JavaScriptUtil(this.driver);
		
		
	}
	
	public String getCartPageHeader() {
		 String pageHeader=elementutil.doGetText(cartPageHeader);
		String text=pageHeader.split(" ")[0]+" "+pageHeader.split(" ")[1];
		 return text;
	}
	
	public String getNextActionsHeader() {
		return elementutil.doGetText(nextActionsHeader);
	}
	
	public List<String> getMacBookProInfo() {
		List<String> productinfolist=new ArrayList<>();
		List<WebElement> productList=elementutil.getElements(macBookProInfo);
		for(WebElement e:productList) {
			if(!e.getText().isEmpty()) {
			System.out.println(e.getText());
			productinfolist.add(e.getText());
			}
		}
		return productinfolist;
	}
	public List<String> getPreCheckoutAction() {
		List<String> checkoutOptionList=new ArrayList<>();
		List<WebElement> precheckoutoptionlist=elementutil.getElements(preCheckoutAction);
		for(WebElement e:precheckoutoptionlist) {
			System.out.println(e.getText());
			checkoutOptionList.add(e.getText());
		}
		return checkoutOptionList;
	}
	public boolean getEstimationQuotesForUS()  {

		jsutil.scrollIntoView(elementutil.getElement(estimateShippingToggle));
		elementutil.doClick(estimateShippingToggle);
		elementutil.waitForElementToBeLocated(selectCountry, 10);
		elementutil.selectDropDownValueWithoutSelectClass(selectCountry, "United States");
		elementutil.waitForElementToBeLocated(maryLandText, 10);
		elementutil.selectDropDownValueWithoutSelectClass(selectState, "Maryland");
		elementutil.doSendKeys(postCode, "31301");
		elementutil.doClick(getQuotesButton);
		elementutil.waitForElementToBeLocated(flatRateButton, 15).click();
		elementutil.doClick(applyShippingButton);
		return elementutil.waitForElementToBeLocated(successText, 10).isDisplayed();
		
		
	}

}
