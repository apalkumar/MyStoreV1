package com.mystore.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mystore.pageObject.creatAccountPage;
import com.mystore.pageObject.indexPage;
import com.mystore.pageObject.myAccountPage;
import com.mystore.utilities.ReadExcelFile;

@Listeners(com.mystore.utilities.ExtentListenerClass.class)
public class TC_AccountCreationPageTestDataDrivenTesting extends BaseClass {
	
	public boolean str;

	@Test(dataProvider = "AccountCreation")
	public void accountRegistration(String email, String title, String firstname, String lastname, String password, String days,
			String months, String years) throws IOException {
		logger = LogManager.getLogger("MyStoreV1");

		indexPage pg = new indexPage(driver);
		pg.clickOnSign();
		logger.info("Sign in Button Clicked");
		myAccountPage mac = new myAccountPage(driver);
//		mac.enterEmail("sjatin2009@gmail.com");
		mac.enterEmail(email);
		mac.click_crreateAccount();
		logger.info("Create Account Button Clicked");
		
		creatAccountPage cap = new creatAccountPage(driver);
//		cap.selectTitle("ABCD");
		cap.selectTitle(title);
//		cap.enter_FirstName("Anil");
		cap.enter_FirstName(firstname);
		cap.enter_LastName("Pal");
		cap.enter_LastName(lastname);
//		str = cap.validate_EmailID("sjatin2009@gmail.com");
		str = cap.validate_EmailID(email);
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
//		cap.enter_Password("abcdw");
		cap.enter_Password(password);
//		cap.select_days("10");
		cap.select_days(days);
//		cap.select_months("May");
		cap.select_months(months);
//		cap.select_years("2025");
		cap.select_years(years);
		cap.validate_Register();

	
	}
	
	@DataProvider(name="AccountCreation")
	public String[][] LoginInDataProvider() {
	    System.out.println(System.getProperty("user.dir"));
	    String fileName = System.getProperty("user.dir") + "\\testData\\MyStoreData.xlsx";

	    int ttlRows = ReadExcelFile.getRowCount(fileName, "UserRegistrationData");
	    int ttlColumns = ReadExcelFile.getColCount(fileName, "UserRegistrationData"); // FIX HERE

	    String[][] data = new String[ttlRows][ttlColumns];

	    for (int i = 1; i <= ttlRows; i++) {
	        for (int j = 0; j < ttlColumns; j++) {
	            data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "UserRegistrationData", i, j);
	        }
	    }
	    return data;
	}
}
