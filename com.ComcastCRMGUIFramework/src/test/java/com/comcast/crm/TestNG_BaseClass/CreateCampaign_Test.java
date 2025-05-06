package com.comcast.crm.TestNG_BaseClass;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.CampaignInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.CampaignPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateProductPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsPopupWindow;
import com.comcast.crm.generic.BaseUtility.BaseClass;

public class CreateCampaign_Test extends BaseClass
{
	@Test(groups = {"smoke"})
	public void UsingPOMCreateCampaignWithMandatoryFieldAndValidateTest(Method mthd) throws Throwable 
	{
		int rndmno = ju.toGetRandomNumber();
		
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
		du.toValidateActualAndExpectedData("Campaign Info", cip.getCampaignInfo(), campCellData);
		
	//	Verify Expected Assigned To with Actual Assigned To
		du.toValidateActualAndExpectedData("Assigned To", cip.getAssignedToInfo(), assignToOpt);
		
	//	Verify Expected Close Date with Actual Exp. Close Date
		du.toValidateActualAndExpectedData("Expected Close Date Info", cip.getClosingDateInfo(), expDate);
	}
	
	
	@Test(groups = {"regression", "integration"})
	public void UsingPOMCreateCampaignWithProduct_DDAndValidateTest() throws IOException, InterruptedException 
	{
		int rndmno = ju.toGetRandomNumber();
		
	//	Fetch test data from Excel file and Java Utility
		String productCellData = exu.getDataFromExcelFile("campaign", 5, 7) + rndmno;
		String campCellData = exu.getDataFromExcelFile("campaign", 5, 2) + rndmno;
		String assignToOpt = exu.getDataFromExcelFile("campaign", 5, 3);
		String typeOpt = exu.getDataFromExcelFile("campaign", 5, 5);
		String statusOpt = exu.getDataFromExcelFile("campaign", 5, 6);
		
		String expDate = ju.getRequiredDateInDDMMYYYY(120);
		
	//	Navigate to Product Page
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		
	//	Open the Create Product page
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductButton().click();
		
	//	Fill the cell with Product name from Excel file
		CreateProductPage cpp = new CreateProductPage(driver);
		cpp.getProductNameEdit().sendKeys(productCellData);
		cpp.getSaveButton().click();
		
		ProductsInfoPage pip = new ProductsInfoPage(driver);
	//	Verify Header Product name from Expected Product name
		du.toValidateActualAndExpectedData("Product Header", pip.getHeaderMsg(), productCellData);
		
	//	Verify Product name from Expected Product name
		du.toValidateActualAndExpectedData("Product Info", pip.getProductInfo(), productCellData);
		
	//	Navigate to Campaign Page
		hp.navigateToCampaignPage();
		
	//	Open Create Campaign Page
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaignButton().click();
		
	//	Fill all the Data 
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameEdit().sendKeys(campCellData);
		du.selectOptionByVisibleText(ccp.getAssignedToUserDD(), assignToOpt);
		
		WebElement enterDate = ccp.getCloseDateEdit();
		enterDate.clear();
		enterDate.sendKeys(expDate);
		
		du.selectOptionByValue(ccp.getCampaignTypeDD(), typeOpt);
		du.selectOptionByValue(ccp.getCampaignStatusDD(), statusOpt);
		
		ccp.getProductAddImg().click();
		
		String parentID = driver.getWindowHandle();
	//	Select the created Product by Switch to Child Window
		du.switchToChildWindow(driver, "module=Products");
			ProductsPopupWindow ppw = new ProductsPopupWindow(driver);
			ppw.getProdNameEdit().sendKeys(productCellData);
			ppw.getSearchButton().click();
			driver.findElement(By.xpath("//a[text() = '" + productCellData + "']")).click();
		
		driver.switchTo().window(parentID);
		ccp.getSaveButton().click();
		
		
		CampaignInfoPage cip = new CampaignInfoPage(driver);
	//	Verify Expected Assigned To with Actual Assigned To
		du.toValidateActualAndExpectedData("Assigned To info", cip.getAssignedToInfo(), assignToOpt);
		
	//	Verify Expected Campaign Type with Actual Campaign Type
		du.toValidateActualAndExpectedData("Campaign type Info", cip.getCampaignTypeInfo(), typeOpt);
		
	//	Verify Expected Campaign Status with Actual Campaign Status
		du.toValidateActualAndExpectedData("Campaign Status Info", cip.getCampaignStatusInfo(), statusOpt);
		
	//	Verify Expected Campaign Type with Actual Campaign Type
		du.toValidateActualAndExpectedData("Product Info", cip.getCampaignTypeInfo(), typeOpt);
	}
	
}
