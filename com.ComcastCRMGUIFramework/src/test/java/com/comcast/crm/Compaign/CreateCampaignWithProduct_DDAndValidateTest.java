package com.comcast.crm.Compaign;

import java.io.IOException;

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

public class CreateCampaignWithProduct_DDAndValidateTest {

	public static void main(String[] args) throws IOException, InterruptedException 
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
		
	//	Fetch test data from Excel file
		String productCellData = exu.getDataFromExcelFile("campaign", 5, 7) + rndmno;
		String campCellData = exu.getDataFromExcelFile("campaign", 5, 2) + rndmno;
		String assignToOpt = exu.getDataFromExcelFile("campaign", 5, 3);
		String typeOpt = exu.getDataFromExcelFile("campaign", 5, 5);
		String statusOpt = exu.getDataFromExcelFile("campaign", 5, 6);
		
	//	Open the Create Product page
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Product...']")).click();
		
	//	Fill the cell with Product name from Excel file
		driver.findElement(By.name("productname")).sendKeys(productCellData);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Header Product name from Expected Product name
		System.out.println("-----Product Header Validation-----");
		String prodHeaderInfo = driver.findElement(By.xpath("//span[@class = 'lvtHeaderText']")).getText();
		if(prodHeaderInfo.contains(productCellData))
			System.out.println(productCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Product name from Expected Product name
		System.out.println("-----Product Data Validation-----");
		String prodInfo = driver.findElement(By.id("dtlview_Product Name")).getText();
		if(prodInfo.equals(productCellData))
			System.out.println(productCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Open Create Campaign Page
		du.toMouseHover(driver, driver.findElement(By.linkText("More")));
		
		du.clickOnElementUsingJS(driver, driver.findElement(By.linkText("Campaigns")));
		du.clickOnElementUsingJS(driver, driver.findElement(By.xpath("//img[@title = 'Create Campaign...']")));
		
		driver.findElement(By.name("campaignname")).sendKeys(campCellData);
		du.selectOptionByVisibleText(driver.findElement(By.name("assigned_user_id")), assignToOpt);
		
		String expDate = ju.getRequiredDateInDDMMYYYY(120);
		WebElement enterDate = driver.findElement(By.name("closingdate"));
		enterDate.clear();
		enterDate.sendKeys(expDate);
		
		WebElement campTypeDD = driver.findElement(By.name("campaigntype"));
		du.selectOptionByValue(campTypeDD, typeOpt);
		
		WebElement campStatusDD = driver.findElement(By.name("campaignstatus"));
		du.selectOptionByValue(campStatusDD, statusOpt);
		
		driver.findElement(By.xpath("//input[@name = 'product_name']/following-sibling::img")).click();
		
		String parentID = driver.getWindowHandle();
	//	Select the created Product by Switch to Child Window
		du.switchToChildWindow(driver, "module=Products");
			driver.findElement(By.id("search_txt")).sendKeys(productCellData);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text() = '" + productCellData + "']")).click();
		
		driver.switchTo().window(parentID);	
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Expected Assigned To with Actual Assigned To
		System.out.println("-----Assigned To info Validation-----");
		String assignToInfo = driver.findElement(By.id("dtlview_Assigned To")).getText();
		if(assignToInfo.contains(assignToOpt))
			System.out.println(assignToOpt + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Campaign Type with Actual Campaign Type
		System.out.println("-----Campaign type info Validation-----");
		String campTInfo = driver.findElement(By.id("dtlview_Campaign Type")).getText();
		if(campTInfo.contains(typeOpt))
			System.out.println(typeOpt + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Campaign Status with Actual Campaign Status
		System.out.println("-----Campaign Status info Validation-----");
		String statusInfo = driver.findElement(By.id("dtlview_Campaign Status")).getText();
		if(statusInfo.contains(statusOpt))
			System.out.println(statusOpt + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Campaign Type with Actual Campaign Type
		System.out.println("-----Product info in Campaign Validation-----");
		String prodCampInfo = driver.findElement(By.linkText("" + productCellData + "")).getText();
		if(prodCampInfo.contains(productCellData))
			System.out.println(productCellData + " =======> Information Verified ====> PASS");
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
