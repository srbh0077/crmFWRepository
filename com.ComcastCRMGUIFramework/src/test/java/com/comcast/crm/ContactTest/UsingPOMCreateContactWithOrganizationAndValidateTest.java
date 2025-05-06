package com.comcast.crm.ContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.ContactInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ContactPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateContactPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateContactWithOrganizationAndValidateTest {

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
		
	//	Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
	//	Open the Create Organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();
		
	//	Fetch Data from Excel File
		String contactCellData = exu.getDataFromExcelFile("contact", 7, 2) + rndmno;
		String orgCellData = exu.getDataFromExcelFile("contact", 7, 3) + rndmno;
		
	//	Fill the cell with one Organization from Excel file
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrgNameEdit().sendKeys(orgCellData);
		cop.getSaveButton().click();
										//OR//
		//cop.createOrganization(orgCellData);
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	//	Verify Header Organization name from Expected Organization name
		System.out.println("-----Organization Header Validation-----");
		String orgHeaderInfo = oip.getHeaderMsg().getText();
		if(orgHeaderInfo.contains(orgCellData))
			System.out.println(orgCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Navigate to Contact module
		hp.getContactLink().click();
		
	//	Open the Create Contact page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();
		
	//	Fill the cell with a Contact name and Organization name from Lookup window from Excel file
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContactAction(contactCellData, "module=Accounts", orgCellData);
													//OR//
		//ccp.getContactNameEdit().sendKeys(contactCellData);
		//ccp.getOrgNameAddImg().click();
	
		/*String parentID = driver.getWindowHandle();
		
		du.switchToChildWindow(driver, "module=Accounts");
			OrganizationPopupWindow opw = new OrganizationPopupWindow(driver);
			opw.getOrgNameEdit().sendKeys(orgCellData);
			opw.getSearchButton().click();
			opw.getOrgNameFound().click();
			//driver.findElement(By.xpath("//a[text() = '" + orgName + "']")).click();
		
		driver.switchTo().window(parentID);

		ccp.getSaveButton().click();*/
		
		ContactInfoPage cip = new ContactInfoPage(driver);
	//	Verify Expected Contact header with Actual Contact name
		System.out.println("-----Contact Header Validation-----");
		String conHeaderInfo = cip.getHeaderMsg().getText();
		if(conHeaderInfo.contains(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Contact Lastname with Actual Contact name
		System.out.println("-----Contact lastname Validation-----");
		String contactInfo = cip.getContactNameInfo().getText();
		if(contactInfo.equals(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		hp.signOutAction();
		
		driver.quit();
	}
}
