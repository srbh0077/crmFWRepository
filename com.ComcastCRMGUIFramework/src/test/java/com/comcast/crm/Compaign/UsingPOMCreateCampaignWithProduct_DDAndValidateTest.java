package com.comcast.crm.Compaign;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.CampaignInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.CampaignPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepositoryUtility.CreateProductPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsPopupWindow;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateCampaignWithProduct_DDAndValidateTest 
{
	public static void main(String[] args) throws IOException, InterruptedException 
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
		
	//	Login to the Application
		LoginPage lp = new LoginPage(driver);
		lp.loginAction(URL, UN, PWD);
		
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
		System.out.println("-----Product info in Campaign Validation-----");
		String prodCampInfo = driver.findElement(By.linkText("" + productCellData + "")).getText();
		if(prodCampInfo.contains(productCellData))
			System.out.println(productCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");

	//	Signout from Application
		hp.signOutAction();
		
		driver.quit();
	}

}
