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

public class UsingPOMCreateContactAndValidateTest {

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
		
	//	Login to the Application
		LoginPage lp = new LoginPage(driver);
		lp.loginAction(URL, UN, PWD);
		
	//	Navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
	//	Open the Create Contact page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();
		
	//	Fetch data from Excel File
		String contactCellData = exu.getDataFromExcelFile("contact", 1, 2) + rndmno;	
		
	//	Fill the cell with a Contact name from Excel file
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getContactNameEdit().sendKeys(contactCellData);
		ccp.getSaveButton().click();
										//OR//
	//	ccp.createContactAction(contactCellData);
		
		
		ContactInfoPage cip = new ContactInfoPage(driver);
	//	Verify Expected Contact Header name with Actual Contact name
		System.out.println("-----Contact Header Validation-----");
		String headerInfo = cip.getHeaderMsg().getText();
		if(headerInfo.contains(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Contact name with Actual Contact name
		System.out.println("-----Contact info Validation-----");
		String contactInfo = cip.getContactNameInfo().getText();
		if(contactInfo.contains(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		du.toMouseHover(driver, hp.getAdminImg());
		hp.getSignOutLink().click();
		
		driver.quit();
	}

}
