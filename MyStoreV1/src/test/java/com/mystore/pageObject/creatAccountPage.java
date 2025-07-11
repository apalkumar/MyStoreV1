package com.mystore.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.testcases.BaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class creatAccountPage {

	WebDriver ldriver;
	public static Logger logger;

	public creatAccountPage(WebDriver rdriver) {

		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);
		logger = LogManager.getLogger("MyStoreV1");

	}

	@FindBy(id = "id_gender1")
	WebElement genderMr;

	@FindBy(id = "id_gender2")
	WebElement genderMrs;

	@FindBy(id = "customer_firstname")
	WebElement customerFirstName;

	@FindBy(id = "customer_lastname")
	WebElement customerLastName;

	@FindBy(name = "email")
	WebElement customerEmailID;

	@FindBy(id = "passwd")
	WebElement customerPwd;

	@FindBy(id = "days")
	WebElement customerDOBdate;

	@FindBy(id = "months")
	WebElement customerDOBmonth;

	@FindBy(id = "years")
	WebElement customerDOByears;

	@FindBy(id = "newsletter")
	WebElement newsletter;

	@FindBy(id = "submitAccount")
	WebElement submitAccount;

	public void selectTitle(String title) {
		if (title == "Mr") {
			genderMr.click();
		} else {
			genderMrs.click();
		}
	}

	public void enter_FirstName(String FName) {
		customerFirstName.sendKeys(FName);
	}

	public void enter_LastName(String LName) {
		customerLastName.sendKeys(LName);
	}

	public boolean validate_EmailID(String Emailid) {
		boolean Flag;
		String fetchedEmailID = customerEmailID.getAttribute("value");
		fetchedEmailID = fetchedEmailID.concat("123");
		if (fetchedEmailID.equals(Emailid)) {
//			logger.info("Same Email ID observed");
			Flag = true;
		} else {
//			logger.info("Different Email ID observed hence closing this test here");
			Flag = false;
//			System.exit(0);
		}
		return Flag;
		
	}

	public void enter_Password(String pwd) {
		customerPwd.sendKeys(pwd);
	}

	public void select_days(String days) {
		customerDOBdate.click();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customerDOBdate.sendKeys(days);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void select_months(String month) {
		customerDOBmonth.click();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customerDOBmonth.sendKeys(month);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void select_years(String year) {
		customerDOByears.click();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customerDOByears.sendKeys(year);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void select_newletter() {
		if (newsletter.isSelected()) {
			logger.info("Newletter is already selected");
		} else {
			newsletter.click();
		}
	}

	public void validate_Register() {
		if (submitAccount.isDisplayed()) {
			logger.info("Register Account button is displayed");
		} else {
			logger.atError();
		}

	}

}
