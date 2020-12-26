package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.testlisteners.ExtentReportListener;
import com.qa.opencart.testlisteners.TestAllureListener;
import com.qa.opencart.utils.Constatnts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: define loing page features....")
@Story("US 101: define the login page class features with title, forgot pwd link and login functionality")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	
	/**
	 * This test case will verify the title of login page
	 */
	@Description("Verify Login Page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifyLoginPageTitleTest() {
		String title=lp.getLoginPageTitle();
		System.out.println("Login Page title is:"+title);
		Assert.assertEquals(title, Constatnts.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void verifyLoginPageTextTest() {
		String text=lp.getTextMsg();
		System.out.println("Login Page Text is :"+text);
		Assert.assertEquals(text, Constatnts.TEXT_MSG);
	}
	
	@Test
	public void verifyLoginPageHeaderTest() {
		String header=lp.getLoginPageHeader();
		System.out.println("Login Page Header is :"+header);
		Assert.assertEquals(header, Constatnts.LOGIN_PAGE_HEADER);
	}
	
	@Test
	public void verifyEmailTextTest() {
		Assert.assertTrue(lp.isEmailTextExist());
	}
	
	@Test
	public void verifyPasswordTextTest() {
		Assert.assertTrue(lp.isPwdTextExist());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void verifyForgotPwdLinkTest() {
		Assert.assertTrue(lp.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=1)
	public void loginTest() {
		lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}
