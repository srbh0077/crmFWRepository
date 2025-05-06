package com.comcast.crm.generic.WebdriverUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ThreadUtilityObject 
{
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}
	
	public static ExtentTest getTest() {
		return test.get();
	}
	
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
}
