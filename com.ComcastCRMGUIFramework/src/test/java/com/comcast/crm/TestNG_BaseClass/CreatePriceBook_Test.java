package com.comcast.crm.TestNG_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.CreatePriceBookPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.PriceBookInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.PriceBookPage;
import com.comcast.crm.generic.BaseUtility.BaseClass;

public class CreatePriceBook_Test extends BaseClass
{
	@Test(groups = {"system"})
	public void UsingPOMCreateAPricebook_DeleteAndValidateTest() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		int rndmno = ju.toGetRandomNumber();
		
	//	Fetch Data from Excel Sheet
		String pbName = exu.getDataFromExcelFile("pricebook", 1, 2) + rndmno;
		String currencyOpt = exu.getDataFromExcelFile("pricebook", 1, 3);
		String priceBookNameOpt = exu.getDataFromExcelFile("pricebook", 1, 4);
		
	//	Navigate to PriceBook page
		HomePage hp = new HomePage(driver);
		du.toMouseHover(driver, hp.getMoreLink());
		hp.getPricebookLink().click();
												//OR//
		//hp.navigateToPricebookPage();
		
	//	Open Create Pricebook Page
		PriceBookPage pp = new PriceBookPage(driver);
		pp.getCreatePriceBookButton().click();
		
	//	Fill all the Details on the Page
		CreatePriceBookPage cpp = new CreatePriceBookPage(driver);
		
		cpp.getPriceBookNameEdit().sendKeys(pbName);
		du.selectOptionByVisibleText(cpp.getCurrencyDD(), currencyOpt);
		cpp.getSaveButton().click();
										//OR//
		//cpp.createPriceBookAction(pbName, currencyOpt);
		
		PriceBookInfoPage pip = new PriceBookInfoPage(driver);
	//	Verify Expected Pricebook Header name with Actual Pricebook name
		du.toValidateActualAndExpectedData("Pricebook Header", pip.getHeaderMsg(), pbName);
		
	//	Verify Expected Pricebook name with Actual Pricebook name
		du.toValidateActualAndExpectedData("Pricebook Info", pip.getPriceBookInfo(), pbName);
		
	//	Verify Expected Currency value with Actual Currency value
		du.toValidateActualAndExpectedData("Currency Info", pip.getCurrencyInfo(), currencyOpt);
		
	//	Search the Created Pricebook
		pip.getPriceBookLink().click();
		
		pp.getSearchForEdit().sendKeys(pbName);
		du.selectOptionByVisibleText(pp.getInDD(), priceBookNameOpt);
		pp.getSearchButton().click();
											//OR//
		//pp.priceBookNameSearchAction(pbName, priceBookNameOpt);
		
	//	Delete the related PriceBook
		driver.findElement(By.xpath("//a[text() = '" + pbName + "']/../..//input[@name = 'selected_id']")).click();
		pp.getDeleteButton().click();
												//OR//
		//driver.findElement(By.xpath("//a[text() = '" + pbName + "']/../..//a[text() = 'del']")).click();
		//Handle Notification
		String popupText = du.toGetTextOfAlertPopup(driver);
		System.out.println("\nDelete Popup Text: " + popupText + "\n");
		du.toAcceptAlertPopup(driver);
		
	//	Verify Expected Pricebook got deleted
		du.toValidateActualAndExpectedData(pbName, pip.getNoPriceBookFoundText(), "No PriceBook Found !");
	}

}
