package com.comcast.crm.LeadTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.CreateLeadsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LeadsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.LeadsPage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateLeadWithAllDropDownsAndValidateTest 
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
		
	//	Fetch test data from Excel file
		String leadCellData = exu.getDataFromExcelFile("lead", 1, 2) + rndmno;
		String companyCellData = exu.getDataFromExcelFile("lead", 1, 3);
		String leadSourceCellData = exu.getDataFromExcelFile("lead", 1, 4);
		String industryCellData = exu.getDataFromExcelFile("lead", 1, 5);
		String leadStatusCellData = exu.getDataFromExcelFile("lead", 1, 6);
		String ratingCellData = exu.getDataFromExcelFile("lead", 1, 7);
		
	//	Navigate to Leads Page
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		
	//	Open the Create Lead page
		LeadsPage ldp = new LeadsPage(driver);
		ldp.getCreateLeadButton().click();
		
	//	Fill the cell with all Data from Excel file and Save
		CreateLeadsPage clp = new CreateLeadsPage(driver);
		clp.getLeadNameEdit().sendKeys(leadCellData);
		clp.getCompanyEdit().sendKeys(companyCellData);
		
		du.selectOptionByValue(clp.getLeadSourceDD(), leadSourceCellData);
		du.selectOptionByValue(clp.getIndustryDD(), industryCellData);
		du.selectOptionByValue(clp.getLeadStatusDD(), leadStatusCellData);
		du.selectOptionByValue(clp.getRatingDD(), ratingCellData);
		
		clp.getSaveButton().click();
		
		
		LeadsInfoPage lip = new LeadsInfoPage(driver);
	//	Verify Lead Header Data from Expected data
		System.out.println("-----Lead Header Validation-----");
		String headerInfo = lip.getHeaderMsg().getText();
		if(headerInfo.contains(leadCellData))
			System.out.println(leadCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify actual Lead name from Expected lead name
		System.out.println("-----Lead info Validation-----");
		String leadTextInfo = lip.getLeadNameInfo().getText();
		if(leadTextInfo.contains(leadCellData))
			System.out.println(leadCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify actual Company name from Expected company name
		System.out.println("-----Company info Validation-----");
		String companyInfo = lip.getCompanyInfo().getText();
		if(companyInfo.contains(companyCellData))
			System.out.println(companyCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Industry from Actual Industry
		System.out.println("-----Industry Validation-----");
		String industryInfo = lip.getIndustryInfo().getText();
		if(industryInfo.contains(industryCellData))
			System.out.println(industryCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Lead Source from Actual Lead Source
		System.out.println("-----Lead Source Validation-----");
		String ldsrcInfo = lip.getLeadSourceInfo().getText();
		if(ldsrcInfo.contains(leadSourceCellData))
			System.out.println(leadSourceCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Lead Status from Actual Lead Status
		System.out.println("-----Lead Status Validation-----");
		String ldstsInfo = lip.getLeadStatusInfo().getText();
		if(ldstsInfo.contains(leadStatusCellData))
			System.out.println(leadStatusCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Rating from Actual Rating
		System.out.println("-----Rating Validation-----");
		String ratingInfo = lip.getRatingInfo().getText();
		if(ratingInfo.contains(ratingCellData))
			System.out.println(ratingCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		hp.signOutAction();
			
		driver.quit();
	}

}
