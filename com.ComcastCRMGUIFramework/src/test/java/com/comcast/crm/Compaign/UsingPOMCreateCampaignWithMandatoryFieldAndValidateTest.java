package com.comcast.crm.Compaign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.CampaignInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.CampaignPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateCampaignWithMandatoryFieldAndValidateTest {

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
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameEdit().sendKeys(UN);
		lp.getPasswordEdit().sendKeys(PWD);
		lp.getLoginButton().click();
		
	//	Navigate to Campaign Page and Open Create Campaign Page
		HomePage hp = new HomePage(driver);
		du.toMouseHover(driver, hp.getMoreLink());
		hp.getCampaignsLink().click();
										//OR//
		//hp.navigateToCampaignPage();
		
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaignButton().click();
		
	//	Fetch Data from Utility and Excel File		
		String expDate = ju.getRequiredDateInDDMMYYYY(60);
		String campCellData = exu.getDataFromExcelFile("campaign", 1, 2) + rndmno;
		String assignToOpt = exu.getDataFromExcelFile("campaign", 1, 3);
		
	//	Fill all data in to Create Campaign
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameEdit().sendKeys(campCellData);
		ccp.getAssignedToGroupChkBox().click();
		du.selectOptionByVisibleText(ccp.getAssignedToGroupDD(), assignToOpt);
		
		ccp.getCloseDateEdit().clear();
		ccp.getCloseDateEdit().sendKeys(expDate);
		
		ccp.getSaveButton().click();
		
		CampaignInfoPage cip = new CampaignInfoPage(driver);
	//	Verify Expected Campaign Header name with Actual Campaign name
		du.toValidateActualAndExpectedData("Campaign Header", cip.getHeaderMsg(), campCellData);
		/*System.out.println("-----Campaign Header Validation-----");
		String headerInfo = cip.getHeaderMsg().getText();
		if(headerInfo.contains(campCellData))
			System.out.println(campCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");		*/
		
	//	Verify Expected Campaign name with Actual Campaign name
		System.out.println("-----Campaign info Validation-----");
		String campInfo = cip.getCampaignInfo().getText();
		if(campInfo.contains(campCellData))
			System.out.println(campCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Assigned To with Actual Assigned To
		System.out.println("-----Assigned To info Validation-----");
		String assignToInfo = cip.getAssignedToInfo().getText();
		if(assignToInfo.contains(assignToInfo))
			System.out.println(assignToInfo + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Close Date with Actual Exp. Close Date
		System.out.println("-----Exp. Close Date info Validation-----");
		String expDateInfo = cip.getClosingDateInfo().getText();
		if(expDateInfo.contains(expDate))
			System.out.println(expDate + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");

	//	Signout from Application
		hp.getAdminImg().click();
		hp.getSignOutLink().click();
		
		
		driver.quit();
	}
}
