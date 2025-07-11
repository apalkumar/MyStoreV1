package com.mystore.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class myAccountPage {
	
	WebDriver ldriver;
	
	public myAccountPage(WebDriver rdriver) {
		
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(id = "email_create")
	WebElement emailid;
	
	@FindBy(id = "SubmitCreate")
	WebElement btnSubmit;
	
	public void enterEmail(String email) {
		emailid.sendKeys(email);
	}
	
	public void click_crreateAccount() {
		btnSubmit.click();
	}
	
	

}
