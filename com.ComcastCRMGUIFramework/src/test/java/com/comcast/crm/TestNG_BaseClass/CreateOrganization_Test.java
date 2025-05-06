package com.comcast.crm.TestNG_BaseClass;

import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.crm.generic.BaseUtility.BaseClass;

public class CreateOrganization_Test extends BaseClass
{
	@Test(groups = {"smoke"})
	public void UsingPOMCreateOrganizationAndValidateTest() throws Throwable 
	{		
		int rndmno = ju.toGetRandomNumber();
		
	//	Login to the Application
		//LoginPagePublic lp = PageFactory.initElements(driver, LoginPagePublic.class);
		//lp.getUsernameEdit().sendKeys("admin");
		//lp.usernameEdit.sendKeys("admin");
												//OR//
		//All login action present in base class 
		
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
		du.toValidateActualAndExpectedData("Organization Header", oip.getHeaderMsg(), orgCellData);
		
	//	Verify actual Organization name from Expected Organization name
		du.toValidateActualAndExpectedData("Organization Info", oip.getOrgNameInfo(), "amit");
	}
	
	
	@Test(groups = {"regression"})
	public void UsingPOMCreateOrganizationWIthIndustry_TypeEtcANdValidateTest() throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
		System.out.println(rndmno);
		
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
		du.toValidateActualAndExpectedData("Industry", oip.getIndustryInfo(), industryCellData);
		
	//	Verify Expected Type from Actual Type
		du.toValidateActualAndExpectedData("Type", oip.getTypeInfo(), typeCellData);;
		
	//	Verify Expected AssignedTo from Actual AssignedTo
		du.toValidateActualAndExpectedData("Assigned To", oip.getAssignedToInfo(), assignedToCellData);;
		
	//	Verify Expected Rating from Actual Rating
		du.toValidateActualAndExpectedData("Rating", oip.getRatingInfo(), ratingCellData);;
	}
	
	
	
	@Test(groups = {"regression"})
	public void UsingPOMCreateOrganizationWithPhoneNumAndValidateTest() throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
		
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
		du.toValidateActualAndExpectedData("Phone Number", oip.getPhoneInfo(), phoneCellData);
	}
	
}
