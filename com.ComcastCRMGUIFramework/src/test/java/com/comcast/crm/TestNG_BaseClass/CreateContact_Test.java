package com.comcast.crm.TestNG_BaseClass;

import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.ContactInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ContactPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateContactPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.crm.generic.BaseUtility.BaseClass;

public class  CreateContact_Test extends BaseClass
{
	@Test(groups = {"smoke"})
	public void UsingPOMCreateContactAndValidateTest() throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
	
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
		du.toValidateActualAndExpectedData("Contact Header", cip.getHeaderMsg(), contactCellData);
		
	//	Verify Expected Contact name with Actual Contact name
		du.toValidateActualAndExpectedData("Contact info", cip.getContactNameInfo(), contactCellData);
	}

	
	
	@Test(groups = {"regression", "integration"})
	public void UsingPOMCreateContactWithOrganizationAndValidateTest() throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
	
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
		du.toValidateActualAndExpectedData("Organization Header", oip.getHeaderMsg(), orgCellData);
		
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
		du.toValidateActualAndExpectedData("Contact Header", cip.getHeaderMsg(), contactCellData);
		
	//	Verify Expected Contact Lastname with Actual Contact name
		du.toValidateActualAndExpectedData("Contact lastname", cip.getContactNameInfo(), contactCellData);
	}
	
	
	
	@Test(groups = {"regression"})
	public void UsingPOMCreateContactWithSupportDateAndValidateTest() throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
		
	//	Navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
	//	Open the Create Contact page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();
		
	//	Fetch Data from Excel File and JavaUtility
		String contactCellData = exu.getDataFromExcelFile("contact", 4, 2) + rndmno;	
		
		String currentDate = ju.getSystemDateInDDMMYYYY();
		String endDate = ju.getRequiredDateInDDMMYYYY(30);
		
	//	Fill the cell with Contact name and preconditions from Excel file
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContactAction(contactCellData, 30);
									//OR//
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
		du.toValidateActualAndExpectedData("Support Start Date", cip.getSupportStartDateInfo(), currentDate);
		
	//	Verify Expected End Date with Actual End Date
		du.toValidateActualAndExpectedData("Support End Date", cip.getSupportEndDateInfo(), endDate);
	}
}
