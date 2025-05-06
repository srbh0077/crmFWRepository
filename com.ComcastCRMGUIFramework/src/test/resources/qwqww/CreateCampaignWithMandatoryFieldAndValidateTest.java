package com.comcast.crm.Compaign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class CreateCampaignWithMandatoryFieldAndValidateTest {

	public static void main(String[] args) throws Throwable 
	{
		PropertyUtility prop = new PropertyUtility();
		DriverUtility du = new DriverUtility();
		ExcelUtility exu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		
		int rndmno = ju.toGetRandomNumber();
		
	//	Fetch common data from property file
		String BROWSER = prop.getDataFromPropertyFile("browser");
		String URL = prop.getDataFromPropertyFile("url");
		String UN = prop.getDataFromPropertyFile("username");
		String PWD = prop.getDataFromPropertyFile("password");
		
		//	Run-Time Polymorphism achieved here
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equalsIgnoreCase("Firefox")) 
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("EDge")) 
			driver = new EdgeDriver();
		else
			driver = new ChromeDriver();
		
		du.waitForPageToLoad(driver, 15);
		du.toMaximize(driver);
		
		driver.get(URL);
		
	//	Login to the Application
		WebElement enterUN = driver.findElement(By.name("user_name"));
		du.insertValueInElementUsingJS(driver, UN, enterUN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		
		driver.findElement(By.id("submitButton")).submit();
		
	//	Open Create Campaign Page
		du.toMouseHover(driver, driver.findElement(By.linkText("More")));
		
		du.clickOnElementUsingJS(driver, driver.findElement(By.linkText("Campaigns")));
		du.clickOnElementUsingJS(driver, driver.findElement(By.xpath("//img[@title = 'Create Campaign...']")));
		
		String campCellData = exu.getDataFromExcelFile("campaign", 1, 2) + rndmno;
		String assignToOpt = exu.getDataFromExcelFile("campaign", 1, 3);
		
		driver.findElement(By.name("campaignname")).sendKeys(campCellData);
		driver.findElement(By.xpath("//input[@value = 'T']")).click();
		du.selectOptionByVisibleText(driver.findElement(By.name("assigned_group_id")), assignToOpt);
		
		String expDate = ju.getRequiredDateInDDMMYYYY(60);
		System.out.println(expDate);
		WebElement enterDate = driver.findElement(By.name("closingdate"));
		enterDate.clear();
		enterDate.sendKeys(expDate);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Expected Campaign Header name with Actual Campaign name
		System.out.println("-----Campaign Header Validation-----");
		String headerInfo = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(headerInfo.contains(campCellData))
			System.out.println(campCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Campaign name with Actual Campaign name
		System.out.println("-----Campaign info Validation-----");
		String campInfo = driver.findElement(By.id("dtlview_Campaign Name")).getText();
		if(campInfo.contains(campCellData))
			System.out.println(campCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Assigned To with Actual Assigned To
		System.out.println("-----Assigned To info Validation-----");
		String assignToInfo = driver.findElement(By.id("dtlview_Assigned To")).getText();
		if(assignToInfo.contains(assignToInfo))
			System.out.println(assignToInfo + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Close Date with Actual Exp. Close Date
		System.out.println("-----Exp. Close Date info Validation-----");
		String expDateInfo = driver.findElement(By.xpath("//td[text() = 'Expected Close Date']/following-sibling::td")).getText();
		if(expDateInfo.contains(expDate))
			System.out.println(expDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");

	//	Signout from Application
		WebElement personIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		du.toMouseHover(driver, personIcon);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text() = 'Sign Out']")).click();
		
		driver.quit();
	}
}
