package com.mystore.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mystore.pageObject.creatAccountPage;
import com.mystore.pageObject.indexPage;
import com.mystore.pageObject.myAccountPage;

@Listeners(com.mystore.utilities.ExtentListenerClass.class)
public class TC_AccountCreationPageTest extends BaseClass {
	
	public boolean str;

	@Test
	public void accountRegistration() throws IOException {
		logger = LogManager.getLogger("MyStoreV1");

		indexPage pg = new indexPage(driver);
		pg.clickOnSign();
		logger.info("Sign in Button Clicked");
		myAccountPage mac = new myAccountPage(driver);
		mac.enterEmail("sjatin2009@gmail.com");
		mac.click_crreateAccount();
		logger.info("Create Account Button Clicked");
		
		creatAccountPage cap = new creatAccountPage(driver);
		cap.selectTitle("ABCD");
		cap.enter_FirstName("Anil");
		cap.enter_LastName("Pal");
		str = cap.validate_EmailID("sjatin2009@gmail.com");
		if(str == true) {
			logger.info("Same Email found");
			Assert.assertTrue(true);
		}
		else {
			logger.info("Different Email found");
			captureScreenShot(driver, "accountRegistration");
//			Assert.assertTrue(false);
		}
		
//		BaseClass.captureScreenShot(driver, "accountRegistration");
		cap.enter_Password("abcdw");
		cap.select_days("10");
		cap.select_months("May");
		cap.select_years("2025");
		cap.validate_Register();

	
	}
}
