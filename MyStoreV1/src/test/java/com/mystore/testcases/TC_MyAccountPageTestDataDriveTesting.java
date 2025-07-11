package com.mystore.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mystore.pageObject.indexPage;
import com.mystore.pageObject.myAccountPage;
import com.mystore.utilities.ReadExcelFile;

public class TC_MyAccountPageTestDataDriveTesting extends BaseClass {

	@Test(dataProvider = "LoginDataProvider")
	public void verifyRegistrationAndLogin(String email) {

		
		indexPage pg = new indexPage(driver);
		pg.clickOnSign();
		logger.info("Sign in Button Clicked");

		myAccountPage mac = new myAccountPage(driver);
		mac.enterEmail(email);
		mac.click_crreateAccount();
		logger.info("Create Account Button Clicked");
	}
	
	@DataProvider(name="LoginDataProvider")
	public String[][] LoginInDataProvider() {
	    System.out.println(System.getProperty("user.dir"));
	    String fileName = System.getProperty("user.dir") + "\\testData\\MyStoreData.xlsx";

	    int ttlRows = ReadExcelFile.getRowCount(fileName, "LogInTestData");
	    int ttlColumns = ReadExcelFile.getColCount(fileName, "LogInTestData"); // FIX HERE

	    String[][] data = new String[ttlRows][ttlColumns];

	    for (int i = 1; i <= ttlRows; i++) {
	        for (int j = 0; j < ttlColumns; j++) {
	            data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "LogInTestData", i, j);
	        }
	    }
	    return data;
	}
}
