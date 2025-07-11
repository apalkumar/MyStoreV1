package com.mystore.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.mystore.pageObject.indexPage;
import com.mystore.utilities.readConfig;

public class BaseClass {

	readConfig rc = new readConfig();
	String url = rc.getBaseUrl();
	String browser = rc.getBrowser();

	public static WebDriver driver;
	public static Logger logger;

	@BeforeClass
	public void setup() {

		logger = LogManager.getLogger("MyStoreV1");
		
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "safari":
			WebDriverManager.safaridriver();
			driver = new SafariDriver();
			break;
		default:
			driver = null;
			break;
		}
		
		if (driver != null) {
			driver.get(url);
			logger.info("URL opened");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Click Sign In
			indexPage pg = new indexPage(driver);
			pg.clickOnSign();
			logger.info("Sign in Button Clicked");
		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	public static void captureScreenShot(WebDriver driver, String testName) throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot as file
        File src = ts.getScreenshotAs(OutputType.FILE);

        // Define destination file path
        File dest = new File(System.getProperty("user.dir") + "//screenshots//" + testName + ".png");

        FileUtils.copyFile(src, dest);
	}
}
