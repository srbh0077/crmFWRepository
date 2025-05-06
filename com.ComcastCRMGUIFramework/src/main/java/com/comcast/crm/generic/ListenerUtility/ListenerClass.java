package com.comcast.crm.generic.ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.BaseUtility.BaseClass;
import com.comcast.crm.generic.WebdriverUtility.ThreadUtilityObject;

public class ListenerClass implements ITestListener, ISuiteListener
{
	public ExtentReports report;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) 
	{
		System.out.println("onStart: "+suite.getName());

		 
		String timeStamp = new Date().toString().replace(" ", "_").replace(":", "_");
		
	//	Extent Report Configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_" + timeStamp + ".html");
		spark.config().setDocumentTitle("Basic Title");
		spark.config().setReportName("Suite Report");
		spark.config().setTheme(Theme.DARK);
		
	//	set System and Env. Info
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Environment", "Testing");
		report.setSystemInfo("OS", "Windows 10 v19045");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Build", "xyz_22");
		//report.setGherkinDialect(timeStamp);
	}
	
	@Override
	public void onFinish(ISuite suite) 
	{
		System.out.println("onFinish: "+suite.getName());
		
		report.flush();

	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("onTestStart: "+result.getMethod().getMethodName());
		//TestName = method name
		test = report.createTest(result.getMethod().getMethodName());
		
		ThreadUtilityObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + " <===== STARTED =====>");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess: "+result.getMethod().getMethodName());	

		test.log(Status.PASS, result.getMethod().getMethodName() + " <===== PASSED =====>");
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		String timeStamp = new Date().toString().replace(" ", "_").replace(":", "_");
		String testScriptName = result.getMethod().getMethodName() + "_" + timeStamp;
		
		TakesScreenshot ts = (TakesScreenshot) BaseClass.listenerDriver;
		
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp, testScriptName);
		
		File tempFile = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + testScriptName + ".jpg");
		try 
		{
			FileHandler.copy(tempFile, src);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		test.log(Status.FAIL, result.getMethod().getMethodName() + " <===== FAILED =====>");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		System.out.println("onTestSkipped: "+result.getMethod().getMethodName());	

		test.log(Status.SKIP, result.getMethod().getMethodName() + " <===== SKIPPED =====>");
	}

}
