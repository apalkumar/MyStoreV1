package com.mystore.utilities;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentListenerClass implements ITestListener{

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	public String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss").format(new Date());
	public String reportName = "MyStoreTestReport"+timestamp+".html";

	public void configureReport() {
		
		readConfig 	rC = new readConfig();
//		htmlReporter = new ExtentSparkReporter("ExtentListenerReportDemo.html");
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Report\\"+reportName);
//		htmlReporter = new ExtentSparkReporter(reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		// add system information/environment info to report
		reports.setSystemInfo("Machine", "testpc1");
		reports.setSystemInfo("OS", "windows 11");
		reports.setSystemInfo("browser", rC.getBrowser());
		reports.setSystemInfo("username", "prachi");

		//Configuration to change the look and feel of report
		htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlReporter.config().setReportName("This is my first report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	public void onStart(ITestContext Result) {
		configureReport();
		System.out.println("On Start Method invoked");
	}

	public void onFinish(ITestContext Result) {
		System.out.println("On Finish Method invoked");
		System.out.println("Report generated at: " + System.getProperty("user.dir") + "\\Report\\" +reportName);
		reports.flush();
	}

	// when test cases gets failed then below method will be called
	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of the test method failed:"+Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " +Result.getName(), ExtentColor.RED));		
		String screenShotPath = System.getProperty("user.dir")+"\\Screenshots\\"+Result.getName()+".png";
		File screenShotFile = new File(screenShotPath);
		if(screenShotFile.exists()) {
			test.fail("Captured Screen Shot is below:" + test.addScreenCaptureFromPath(screenShotPath));
		}
		
	}
	
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of the test method passed:"+Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " +Result.getName(), ExtentColor.GREEN));		
	}
	
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of the test method failed:"+Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped test case is: " +Result.getName(), ExtentColor.RED));		
	}
	
	public void onTestFailedButWithSuccessPercentage(ITestResult Result) {
		
	}

//	@AfterMethod
//	public void getTestResult(ITestResult result) {
//		if(result.getStatus() == ITestResult.FAILURE) {
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName() +" FAILED ",  ExtentColor.RED));
//		}
//		else if(result.getStatus() == ITestResult.SUCCESS) {
//			test.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName() +" PASSED ",  ExtentColor.GREEN));
//		}else if(result.getStatus() == ITestResult.SKIP) {
//			test.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName() +" SKIPPED ",  ExtentColor.AMBER));
//		}
//
//	}



}
