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

public class UsingPOMCreateOrganizationWIthIndustry_TypeEtcANdValidateTest {

	public static void main(String[] args) throws Throwable 
	{
		PropertyUtility prop = new PropertyUtility();
		DriverUtility du = new DriverUtility();
		ExcelUtility exu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		
		int rndmno = ju.toGetRandomNumber();
		System.out.println(rndmno);
		
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
		LoginPage lp = new LoginPage(driver);			// constructor has been created with PageFactory class
		
		lp.loginAction(URL, UN, PWD);
											//OR//
		/*lp.getUsernameEdit().sendKeys(UN);
		lp.getPasswordEdit().sendKeys(PWD);
		lp.getLoginButton().click();*/
		
	//	Navigate to Organization Page
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
	//	Open the Create Organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();
		
	//	Fetch All Data from the Excel File
		exu.getDataFromExcelFile("org", 4, 2);

		String orgCellData = exu.getDataFromExcelFile("org", 4, 2) + rndmno;
		String industryCellData = exu.getDataFromExcelFile("org", 4, 3);
		String typeCellData = exu.getDataFromExcelFile("org", 4, 4);
		String assignedToCellData = exu.getDataFromExcelFile("org", 4, 5);
		String ratingCellData = exu.getDataFromExcelFile("org", 4, 6);
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
	//	Fill the cell with OrgName from Excel file
		cop.getOrgNameEdit().sendKeys(orgCellData);
		
	//	Fill the cell with Industry from Excel file
		du.selectOptionByVisibleText(cop.getIndustryDD(), industryCellData);
		
	//	Fill the cell with AccountType from Excel file
		du.selectOptionByVisibleText(cop.getTypeDD(), typeCellData);
		
	//	Fill the cell with AssignedTo(Group) from Excel file
		cop.getAssignedToGroupChkBox().click();
		du.selectOptionByVisibleText(cop.getAssignedToGroupDD(), assignedToCellData);

	//	Fill the cell with Rating from Excel file
		du.selectOptionByValue(cop.getRatingDD(), ratingCellData);
		
	//	Click on Save Button
		cop.getSaveButton().click();
		
					//OR Do all Above steps using Business Utility that I have created
		//cop.createOrganizationAction(orgCellData, industryCellData, typeCellData, assignedToCellData, ratingCellData);
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	//	Verify Expected Industry from Actual Industry
		System.out.println("-----Industry Validation-----");
		String industryInfo = oip.getIndustryInfo().getText();
		if(industryInfo.contains(industryCellData))
			System.out.println(industryCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Type from Actual Type
		System.out.println("-----Type Validation-----");
		String typeInfo = oip.getTypeInfo().getText();
		if(typeInfo.contains(typeCellData))
			System.out.println(typeCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected AssignedTo from Actual AssignedTo
		System.out.println("-----Assigned To Validation-----");
		String assignedInfo = oip.getAssignedToInfo().getText();
		if(assignedInfo.contains(assignedToCellData))
			System.out.println(assignedToCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Rating from Actual Rating
		System.out.println("-----Rating Validation-----");
		String ratingInfo = oip.getRatingInfo().getText();
		if(ratingInfo.contains(ratingCellData))
			System.out.println(ratingCellData + " =======> Information Verified ====> PASS");
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
