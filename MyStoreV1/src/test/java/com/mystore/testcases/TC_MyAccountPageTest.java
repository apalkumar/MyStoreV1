package com.mystore.testcases;

import org.testng.annotations.Test;
import com.mystore.pageObject.indexPage;
import com.mystore.pageObject.myAccountPage;

public class TC_MyAccountPageTest extends BaseClass {

	@Test
	public void verifyRegistrationAndLogin() {

		indexPage pg = new indexPage(driver);
		pg.clickOnSign();
		logger.info("Sign in Button Clicked");

		myAccountPage mac = new myAccountPage(driver);
		mac.enterEmail("cs123@gmail.com");
		mac.click_crreateAccount();
		logger.info("Create Account Button Clicked");
	}
}
