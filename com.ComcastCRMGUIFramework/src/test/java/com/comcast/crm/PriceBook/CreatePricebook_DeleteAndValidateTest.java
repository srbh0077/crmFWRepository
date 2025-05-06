package com.comcast.crm.PriceBook;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class CreatePricebook_DeleteAndValidateTest
{
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
		
		driver.get(URL);
		
	//	Login to the Application
		WebElement enterUN = driver.findElement(By.name("user_name"));
		du.insertValueInElementUsingJS(driver, UN, enterUN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		
		driver.findElement(By.id("submitButton")).submit();
		
	//	Open Create Pricebook Page
		du.toMouseHover(driver, driver.findElement(By.linkText("More")));
		
		du.clickOnElementUsingJS(driver, driver.findElement(By.name("Price Books")));
		du.clickOnElementUsingJS(driver, driver.findElement(By.xpath("//img[@title = 'Create Price Book...']")));
		
		String pbName = exu.getDataFromExcelFile("pricebook", 1, 2) + rndmno;
		String currencyOpt = exu.getDataFromExcelFile("pricebook", 1, 3);
		
		driver.findElement(By.name("bookname")).sendKeys(pbName);
		du.selectOptionByVisibleText(driver.findElement(By.name("currency_id")), currencyOpt);
	
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Expected Pricebook Header name with Actual Pricebook name
		System.out.println("-----Pricebook Header Validation-----");
		String headerInfo = driver.findElement(By.xpath("//span[@class = 'lvtHeaderText']")).getText();
		if(headerInfo.contains(pbName))
			System.out.println(pbName + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Pricebook name with Actual Pricebook name
		System.out.println("-----Pricebook info Validation-----");
		String pbInfo = driver.findElement(By.id("mouseArea_Price Book Name")).getText();
		if(pbInfo.contains(pbName))
			System.out.println(pbName + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Currency value with Actual Currency value
		System.out.println("-----Currency info Validation-----");
		String currencyInfo = driver.findElement(By.id("dtlview_Currency")).getText();
		if(currencyInfo.contains(currencyOpt))
			System.out.println(currencyOpt + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Delete created Pricebook
		driver.findElement(By.linkText("Price Books")).click();
		du.insertValueInElementUsingJS(driver, pbName, driver.findElement(By.name("search_text")));
		du.selectOptionByVisibleText(driver.findElement(By.id("bas_searchfield")), "Price Book Name");
		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text() = '" + pbName + "']/../..//input[@name = 'selected_id']")).click();
		driver.findElement(By.xpath("//input[@class = 'crmbutton small delete']")).click();
											//OR//
		//driver.findElement(By.xpath("//a[text() = '" + pbName + "']/../..//a[text() = 'del']")).click();
		
		String popupText = du.toGetTextOfAlertPopup(driver);
		System.out.println("Delete Popup Text: " + popupText);
		du.toAcceptAlertPopup(driver);
		
	//	Verify Expected Pricebook got deleted
		System.out.println("-----Pricebook Deleted Validation-----");
		String pricebookInfo = driver.findElement(By.xpath("//span[@class = 'genHeaderSmall']")).getText();
		if(pricebookInfo.contains("No PriceBook Found !"))
			System.out.println(pbName + " =======> Successfully Deleted ====> PASS");
		else 
			System.out.println(" =======> Pricebook not Deleted ====> FAIL");
		
	//	Signout from Application
		WebElement personIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		du.toMouseHover(driver, personIcon);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text() = 'Sign Out']")).click();
		
		driver.quit();
	}
}
