package com.comcast.crm.OrganizationTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateOrganizationWithPhoneNumAndValidateTest {

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
		
	//	Navigate to Organization Page
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
	//	Open the Create Organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();

	//	Fetch Data from Excel File
		String orgCellData = exu.getDataFromExcelFile("org", 7, 2) + rndmno;	
		String phoneCellData = exu.getDataFromExcelFile("org", 7, 3);
		
	//	Fill the cell with one Organization and Phone Number from Excel file
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrgNameEdit().sendKeys(orgCellData);
		cop.getPhoneEdit().sendKeys(phoneCellData);
		cop.getSaveButton().click();
										//OR//
		//cop.createOrganizationAction(orgCellData, phoneCellData);
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	//	Verify Expected PhoneNo. with Actual PhoneNo.
		System.out.println("-----Phone Number Validation-----");
		String phoneInfo = oip.getPhoneInfo().getText();
		if(phoneInfo.contains(phoneCellData))
			System.out.println(phoneCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		hp.signOutAction();
		
		driver.quit();
	}
}
