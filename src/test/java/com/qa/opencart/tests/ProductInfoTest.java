package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.testlisteners.TestAllureListener;
import com.qa.opencart.utils.Constatnts;

@Listeners(TestAllureListener.class)
public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productPageSetup() {
		accountPage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accountPage.doSearch("Macbook");
		productPage=accountPage.selectTheProductFromResults("MacBook Pro");
	}
	
	@Test(priority = 1)
	public void producImagesCountTest() {
		Assert.assertEquals(productPage.gettotalNoOfProductImages(), 4);
	}
	
	@Test(priority = 2)
	public void verifyProductHeaderTest() {
		Assert.assertEquals(productPage.getProductNameHeader(), Constatnts.PRODUCT_NAME_HEADER);
	}
	
	@Test(priority = 3)
	public void verifyProductInformationTest() {
		Map<String,String> productinforMap=productPage.getProductInformation();
		System.out.println(productinforMap);
		Assert.assertEquals(productinforMap, Constatnts.getProudctInfoMapforMacbookPro());
		
	}
	@Test(priority = 4)
	public void verifyProductQuantity() {
		String quantity="2";
		Assert.assertTrue(productPage.selectQuantity(quantity));
	}
	
	@Test(priority = 5)
	public void verifyAddToCart() {
		Assert.assertTrue(productPage.addToCart());
	}
	@Test(priority = 6)
	public void verifyViewCart() {
		productPage.gotoCart();
	}

}
