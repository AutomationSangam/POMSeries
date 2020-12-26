package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage extends BasePage{
	private WebDriver driver;
	private ElementUtil elementutil;
	
	
	private By productNameHeader=By.cssSelector("#content h1");
	private By productMetadata=By.cssSelector("#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceInformation=By.cssSelector("#content ul.list-unstyled:nth-of-type(2) li");
	private By productQuantity=By.cssSelector("#input-quantity");
	private By addToCartButton=By.cssSelector("button#button-cart");
	private By productImages=By.cssSelector(".thumbnails li img");
	private By successAlert=By.cssSelector(".alert-success");
	private By goToCart=By.cssSelector("#cart-total");
	private By viewCart=By.xpath("//strong[contains(text(),'View Cart')]");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(driver);
		}
	
	
	public String getProductNameHeader() {
		return elementutil.doGetText(productNameHeader);
	}
	
	public Map<String,String> getProductInformation() {
		Map<String,String> productinfoforMap=new HashMap<>();
		List<WebElement> productMetadatlist=elementutil.getElements(productMetadata);
		for(WebElement e:productMetadatlist) {
			productinfoforMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> productPriceInfoList=elementutil.getElements(productPriceInformation);
		productinfoforMap.put("price", productPriceInfoList.get(0).getText());
		productinfoforMap.put(productPriceInfoList.get(1).getText().split(":")[0].trim(), productPriceInfoList.get(1).getText().split(":")[1].trim());
		
		return productinfoforMap;
	}
	public boolean selectQuantity(String quantity) {
		elementutil.doSendKeys(productQuantity, quantity);
		return true;
	}
	public boolean addToCart() {
		elementutil.doClick(addToCartButton);
		return elementutil.waitForElementToBeLocated(successAlert, 10).isDisplayed();
	}
	public int gettotalNoOfProductImages() {
		return elementutil.getElements(productImages).size();
	}
	public CartPage gotoCart() {
		elementutil.doClick(goToCart);
		elementutil.waitForElementToBeLocated	(viewCart, 10).click();
		return new CartPage(driver);
		
	}

}
