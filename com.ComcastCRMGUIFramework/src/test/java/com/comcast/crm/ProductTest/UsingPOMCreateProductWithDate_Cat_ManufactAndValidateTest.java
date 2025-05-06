package com.comcast.crm.ProductTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.CreateProductPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateProductWithDate_Cat_ManufactAndValidateTest {

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
	
	//	Login to the Application
		LoginPage lp = new LoginPage(driver);
		lp.loginAction(URL, UN, PWD);
		
	//	Fetch test data from Excel file and Java Utility
		String productCellData = exu.getDataFromExcelFile("product", 1, 2) + rndmno;
		String prodCategoryCellData = exu.getDataFromExcelFile("product", 1, 3);
		String manufactureCellData = exu.getDataFromExcelFile("product", 1, 5);
		
		String currentDate = ju.getSystemDateInDDMMYYYY();
		String salesEndDate = ju.getRequiredDateInDDMMYYYY(7);
		String supportExpDate = ju.getRequiredDateInDDMMYYYY(180);
		
	//	Navigate to Products Page
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		
	//	Open the Create Product page
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductButton().click();
		
	//	Fill all required Cell data
		CreateProductPage cpp = new CreateProductPage(driver);
		cpp.getProductNameEdit().sendKeys(productCellData);
		cpp.getSalesStartDateEdit().sendKeys(currentDate);
		cpp.getSalesEndDateEdit().sendKeys(salesEndDate);
		cpp.getSupportExpDateEdit().sendKeys(supportExpDate);
		
		du.selectOptionByValue(cpp.getProdCategoryDD(), prodCategoryCellData);
		du.selectOptionByValue(cpp.getManufactureDD(), manufactureCellData);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
		
		ProductsInfoPage pip = new ProductsInfoPage(driver);
	//	Verify Header Product name from Expected Product name
		System.out.println("-----Product Header Validation-----");
		String prodHeaderInfo = pip.getHeaderMsg().getText();
		if(prodHeaderInfo.contains(productCellData))
			System.out.println(productCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Product name from Expected Product name
		System.out.println("-----Product Data Validation-----");
		String prodInfo = pip.getProductInfo().getText();
		if(prodInfo.equals(productCellData))
			System.out.println(productCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify start sales date from Expected sales date
		System.out.println("-----Sales Start Date Validation-----");
		String salesStartInfo = pip.getSalesStDateInfo().getText();
		if(salesStartInfo.equals(currentDate))
			System.out.println(currentDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify end sales date from Expected sales end date
		System.out.println("-----Sales End Date Validation-----");
		String salesEndInfo = pip.getSalesEndDateInfo().getText();
		if(salesEndInfo.equals(salesEndDate))
			System.out.println(salesEndDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Sales Category from Expected Sales Category
		System.out.println("-----Sales Category Validation-----");
		String saleCatInfo = pip.getProdCategoryInfo().getText();
		if(saleCatInfo.equals(prodCategoryCellData))
			System.out.println(prodCategoryCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Manufacturer from Expected Manufacturer
		System.out.println("-----Manufacturer Validation-----");
		String manufactureInfo = pip.getManufactureInfo().getText();
		if(manufactureInfo.equals(manufactureCellData))
			System.out.println(manufactureCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify support expiry date from Expected expected expiry date
		System.out.println("-----Support Expiry Date Validation-----");
		String supportEndInfo = pip.getSupportExpDateInfo().getText();
		if(supportEndInfo.equals(supportExpDate))
			System.out.println(supportExpDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		hp.signOutAction();
		
		driver.quit();
	}

}
