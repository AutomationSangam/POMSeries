package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constatnts;

public class CartPageTest extends BaseTest {
	
	
	@BeforeClass
	public void productPageSetup() {
		accountPage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accountPage.doSearch("Macbook");
		productPage=accountPage.selectTheProductFromResults("MacBook Pro");
		productPage.addToCart();
		cartPage=productPage.gotoCart();
	}
	
	@Test
	public void getEstimationQuoteForUSTest()  {
		Assert.assertTrue(cartPage.getEstimationQuotesForUS());
	}
	
	@Test
	public void getCartPageHeaderTest() {
		Assert.assertEquals(cartPage.getCartPageHeader(), Constatnts.CART_PAGE_HEADER);
	}
	
	@Test
	public void nextActionHeaderTest() {
		Assert.assertEquals(cartPage.getNextActionsHeader(), Constatnts.CART_PAGE_NEXT_ACTION_HEADER);
	}
	
//	@Test
//	public void macBookProInfoTest() {
//		cartPage.getMacBookProInfo();
//	}
	
	@Test
	public void preCheckoutOptionTest() {
		Assert.assertEquals(cartPage.getPreCheckoutAction(),Constatnts.NextActionHeader());
	}

}
