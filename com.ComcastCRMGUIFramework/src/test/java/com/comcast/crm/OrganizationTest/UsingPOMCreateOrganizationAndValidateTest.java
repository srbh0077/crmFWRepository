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

public class UsingPOMCreateOrganizationAndValidateTest {

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
		//LoginPagePublic lp = PageFactory.initElements(driver, LoginPagePublic.class);
		//lp.getUsernameEdit().sendKeys("admin");
		//lp.usernameEdit.sendKeys("admin");
												//OR//
		//lp.loginAction(URL, UN, PWD);
												//OR//
		LoginPage lp = new LoginPage(driver);			// constructor has been created with PageFactory class
		lp.getUsernameEdit().sendKeys(UN);
		lp.getPasswordEdit().sendKeys(PWD);
		lp.getLoginButton().click();
		
	//	Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
	//	Open the Create Organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();
		
	//	Fill the cell with one Organization from Excel file
		String orgCellData = exu.getDataFromExcelFile("org", 1, 2) + rndmno;
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrgNameEdit().sendKeys(orgCellData);
		cop.getSaveButton().click();
		
		//cop.createOrganization(orgCellData);
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	//	Verify Header Data from Expected data
		System.out.println("-----Organization Header Validation-----");
		String headerInfo = oip.getHeaderMsg().getText();
		if(headerInfo.contains(orgCellData))
			System.out.println(orgCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify actual Organization name from Expected Organization name
		System.out.println("-----Organization info Validation-----");
		String orgTextInfo = oip.getOrgNameInfo().getText();
		if(orgTextInfo.contains(orgCellData))
			System.out.println(orgCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		du.toMouseHover(driver, hp.getAdminImg());
		hp.getSignOutLink().click();
								//OR//
		//hp.signOutAction();
		
		driver.quit();
	}
}
