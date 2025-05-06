package com.comcast.crm.PriceBook;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.ObjectRepositoryUtility.CreatePriceBookPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.PriceBookInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.PriceBookPage;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class UsingPOMCreateAPricebook_DeleteAndValidateTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException 
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
		System.out.println("-----Pricebook Header Validation-----");
		String headerInfo = pip.getHeaderMsg().getText();
		if(headerInfo.contains(pbName))
			System.out.println(pbName + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Pricebook name with Actual Pricebook name
		System.out.println("-----Pricebook info Validation-----");
		String pbInfo = pip.getPriceBookInfo().getText();
		if(pbInfo.contains(pbName))
			System.out.println(pbName + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Currency value with Actual Currency value
		System.out.println("-----Currency info Validation-----");
		String currencyInfo = pip.getCurrencyInfo().getText();
		if(currencyInfo.contains(currencyOpt))
			System.out.println(currencyOpt + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
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
		System.out.println("-----Pricebook Deleted Validation-----");
		String pricebookInfo = pp.getNoPriceBookFoundText().getText();
		if(pricebookInfo.contains("No PriceBook Found !"))
			System.out.println(pbName + " =======> Successfully Deleted ====> PASS");
		else 
			System.out.println(" =======> Pricebook not Deleted ====> FAIL");
		
	//	Signout from Application
		hp.signOutAction();
		
		driver.quit();
	}

}
