package com.comcast.crm.TestNG_BaseClass;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.CreateProductPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.ProductsPage;
import com.comcast.crm.generic.BaseUtility.BaseClass;

public class CreateProducts_Test extends BaseClass
{
	@Test(groups = {"regression"})
	public void UsingPOMCreateProductWithDate_Cat_ManufactAndValidateTest() throws Throwable 
	{	
		int rndmno = ju.toGetRandomNumber();
		
	//	Fetch test data from Excel file and Java Utility
		String productCellData = exu.getDataFromExcelFile("product", 1, 2) + rndmno;
		String prodCategoryCellData = exu.getDataFromExcelFile("product", 1, 3);
		String manufactureCellData = exu.getDataFromExcelFile("product", 1, 5);
		
		String currentDate = ju.getSystemDateInDDMMYYYY();
		String salesEndDate = ju.getRequiredDateInDDMMYYYY(7);
		String supportExpDate = ju.getRequiredDateInDDMMYYYY(180);
		
	//	Navigate to Products Page
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		
	//	Open the Create Product page
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductButton().click();
		
	//	Fill all required Cell data
		CreateProductPage cpp = new CreateProductPage(driver);
		cpp.getProductNameEdit().sendKeys(productCellData);
		cpp.getSalesStartDateEdit().sendKeys(currentDate);
		cpp.getSalesEndDateEdit().sendKeys(salesEndDate);
		cpp.getSupportExpDateEdit().sendKeys(supportExpDate);
		
		du.selectOptionByValue(cpp.getProdCategoryDD(), prodCategoryCellData);
		du.selectOptionByValue(cpp.getManufactureDD(), manufactureCellData);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
		ProductsInfoPage pip = new ProductsInfoPage(driver);
	//	Verify Header Product name from Expected Product name
		du.toValidateActualAndExpectedData("Product Header", pip.getHeaderMsg(), productCellData);
		
	//	Verify Product name from Expected Product name
		du.toValidateActualAndExpectedData("Product Data", pip.getProductInfo(), productCellData);
		
	//	Verify start sales date from Expected sales date
		du.toValidateActualAndExpectedData("Sales Start Date", pip.getSalesStDateInfo(), currentDate);
		
	//	Verify end sales date from Expected sales end date
		du.toValidateActualAndExpectedData("Sales End Date", pip.getSalesEndDateInfo(), salesEndDate);
		
	//	Verify Sales Category from Expected Sales Category
		du.toValidateActualAndExpectedData("Sales Category", pip.getSupportExpDateInfo(), supportExpDate);
		
	//	Verify Manufacturer from Expected Manufacturer
		du.toValidateActualAndExpectedData("Manufacturer", pip.getManufactureInfo(), manufactureCellData);
		
	//	Verify Support Expiry date from Expected expected Expiry date
		du.toValidateActualAndExpectedData("Support Expiry", pip.getSupportExpDateInfo(), supportExpDate);
	}
}
