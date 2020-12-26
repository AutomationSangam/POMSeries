package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constatnts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.qa.opencart.testlisteners.TestAllureListener;

@Listeners(TestAllureListener.class)
@Epic("EPIC-200: Design Accounts Page")
@Story("US - 201: desiging the accounts page with title, header, account sections and product results..")
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountsPageSetup() {
		accountPage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("verfiyAccountPageTitleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verfiyAccountPageTitleTest() {
		String title=accountPage.getAccountsPageTitle();
		System.out.println("Accounts Page title is :"+title);
		Assert.assertEquals(title, Constatnts.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyPageHeaderTest() {
		String header=accountPage.getAccountsPageHeader();
		System.out.println("Account Page Header is :"+header);
		Assert.assertEquals(header, Constatnts.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyTotalnoofAccountSectionTest() {
		Assert.assertEquals(accountPage.getAccountSectionsCount(), Constatnts.ACCOUNTS_SECTIONS);
	}
	
	@Description("verifyAccountSectionListTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void verifyAccountSectionListTest() {
		Assert.assertEquals(accountPage.getAccountSectionsList(), Constatnts.getAccountSectionList());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void searchTest() {
		Assert.assertTrue(accountPage.doSearch("imac"));
	}

}
