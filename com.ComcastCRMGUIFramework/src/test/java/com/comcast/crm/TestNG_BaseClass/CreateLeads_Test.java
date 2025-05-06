package com.comcast.crm.TestNG_BaseClass;

import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.CreateLeadsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LeadsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.LeadsPage;
import com.comcast.crm.generic.BaseUtility.BaseClass;

public class  CreateLeads_Test extends BaseClass
{
	@Test(groups = {"regression"})
	public void UsingPOMCreateLeadWithAllDropDownsAndValidateTest() throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
		
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
		du.toValidateActualAndExpectedData("Lead Header", lip.getHeaderMsg(),leadCellData);
		
	//	Verify actual Lead name from Expected lead name
		du.toValidateActualAndExpectedData("Lead Info", lip.getLeadNameInfo(), leadCellData);
		
	//	Verify actual Company name from Expected company name
		du.toValidateActualAndExpectedData("Company Info", lip.getCompanyInfo(), companyCellData);
		
	//	Verify Expected Industry from Actual Industry
		du.toValidateActualAndExpectedData("Industry", lip.getIndustryInfo(), industryCellData);
		
	//	Verify Expected Lead Source from Actual Lead Source
		du.toValidateActualAndExpectedData("Lead Source", lip.getLeadSourceInfo(), leadSourceCellData);
		
	//	Verify Expected Lead Status from Actual Lead Status
		du.toValidateActualAndExpectedData("Lead Status", lip.getLeadStatusInfo(), leadStatusCellData);
		
	//	Verify Expected Rating from Actual Rating
		du.toValidateActualAndExpectedData("Rating", lip.getRatingInfo(), ratingCellData);
	}

}
