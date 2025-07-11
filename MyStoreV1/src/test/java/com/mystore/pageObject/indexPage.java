package com.mystore.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class indexPage {
	
	//1. Create Object of WebDriver
	WebDriver ldriver;
	
	public indexPage(WebDriver rdriver) {
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	// identify webElement 
	@FindBy(linkText = "Sign in")
	WebElement lnkSignIn;
	
	// identify action on WebElement
	public void clickOnSign() {
		lnkSignIn.click();
	}

}

