package com.comcast.crm.ContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.ContactInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ContactPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateContactPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateContactWithSupportDateAndValidateTest 
{
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
		
	//	Navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
	//	Open the Create Contact page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();
		
		
	//	Fetch Data from Excel File
		String contactCellData = exu.getDataFromExcelFile("contact", 4, 2) + rndmno;	
		
	//	Fill the cell with Contact name and preconditions from Excel file
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContactAction(contactCellData, 30);
									//OR//
		String currentDate = ju.getSystemDateInDDMMYYYY();
		String endDate = ju.getRequiredDateInDDMMYYYY(30);
	/*	
		ccp.getContactNameEdit().sendKeys(contactCellData);
		
		ccp.getSupportStartDateEdit().clear();
		ccp.getSupportStartDateEdit().sendKeys(currentDate);
		
		ccp.getSupportEndDateEdit().clear();
		ccp.getSupportEndDateEdit().sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
	*/	
		ContactInfoPage cip = new ContactInfoPage(driver);
	//	Verify Expected Start Date. with Actual Start Date
		System.out.println("-----Support Start Date Validation-----");
		String startDateInfo = cip.getSupportStartDateInfo().getText();
		if(startDateInfo.contains(currentDate))
			System.out.println(currentDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected End Date with Actual End Date
		System.out.println("-----Support End Date Validation-----");
		String endDateInfo = cip.getSupportEndDateInfo().getText();
		if(endDateInfo.contains(endDate))
			System.out.println(endDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		hp.signOutAction();
		
		driver.quit();
	}

}
