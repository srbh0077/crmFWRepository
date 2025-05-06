package com.comcast.crm.ProductTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class CreateProductWithDate_Cat_ManufactAndValidateTest {

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
		
		WebDriver driver = null;	// local variable
	//	Run-Time Polymorphism achieved here
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
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		
		driver.findElement(By.id("submitButton")).submit();
		
	//	Fetch test data from Excel file and Java Utility
		String productCellData = exu.getDataFromExcelFile("product", 1, 2) + rndmno;
		String prodCategoryCellData = exu.getDataFromExcelFile("product", 1, 3);
		String manufactureCellData = exu.getDataFromExcelFile("product", 1, 5);
		
		String currentDate = ju.getSystemDateInDDMMYYYY();
		String salesEndDate = ju.getRequiredDateInDDMMYYYY(7);
		String supportExpDate = ju.getRequiredDateInDDMMYYYY(180);
		
	//	Open the Create Product page
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Product...']")).click();
		
	//	Fill the cell with Product name from Excel file
		driver.findElement(By.name("productname")).sendKeys(productCellData);
		
		driver.findElement(By.name("sales_start_date")).sendKeys(currentDate);
		driver.findElement(By.name("sales_end_date")).sendKeys(salesEndDate);
		driver.findElement(By.name("expiry_date")).sendKeys(supportExpDate);
		
		driver.findElement(By.name("productcategory")).sendKeys(prodCategoryCellData);
		driver.findElement(By.name("manufacturer")).sendKeys(manufactureCellData);
		
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
		
	//	Verify start sales date from Expected expected sales date
		System.out.println("-----Sales Start Date Validation-----");
		String salesStartInfo = driver.findElement(By.id("dtlview_Sales Start Date")).getText();
		if(salesStartInfo.equals(currentDate))
			System.out.println(currentDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify end sales date from Expected expected sales end date
		System.out.println("-----Sales End Date Validation-----");
		String salesEndInfo = driver.findElement(By.id("dtlview_Sales End Date")).getText();
		if(salesEndInfo.equals(salesEndDate))
			System.out.println(salesEndDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Sales Category from Expected Sales Category
		System.out.println("-----Sales Category Validation-----");
		String saleCatInfo = driver.findElement(By.id("dtlview_Product Category")).getText();
		if(saleCatInfo.equals(prodCategoryCellData))
			System.out.println(prodCategoryCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Manufacturer from Expected Manufacturer
		System.out.println("-----Manufacturer Validation-----");
		String manufactureInfo = driver.findElement(By.id("dtlview_Manufacturer")).getText();
		if(manufactureInfo.equals(manufactureCellData))
			System.out.println(manufactureCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify support expiry date from Expected expected expiry date
		System.out.println("-----Support Expiry Date Validation-----");
		String supportEndInfo = driver.findElement(By.id("dtlview_Support Expiry Date")).getText();
		if(supportEndInfo.equals(supportExpDate))
			System.out.println(supportExpDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		du.toMouseHover(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text() = 'Sign Out']")).click();
		
		driver.quit();
	}

}
