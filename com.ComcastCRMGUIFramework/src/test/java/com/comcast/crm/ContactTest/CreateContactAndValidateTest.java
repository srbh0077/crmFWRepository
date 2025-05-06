package com.comcast.crm.ContactTest;

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

public class CreateContactAndValidateTest {

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
		
	//	Open the Create Contact page
		WebElement goToContact = driver.findElement(By.linkText("Contacts"));
		du.clickOnElementUsingJS(driver, goToContact);
		driver.findElement(By.xpath("//img[@title = 'Create Contact...']")).click();

	//	Fetch data from Excel File
		String contactCellData = exu.getDataFromExcelFile("contact", 1, 2) + rndmno;	
		
	//	Fill the cell with a Contact name from Excel file
		driver.findElement(By.name("lastname")).sendKeys(contactCellData);
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Expected Contact Header name with Actual Contact name
		System.out.println("-----Contact Header Validation-----");
		String headerInfo = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(headerInfo.contains(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Contact name with Actual Contact name
		System.out.println("-----Contact info Validation-----");
		String contactInfo = driver.findElement(By.id("mouseArea_Last Name")).getText();
		if(contactInfo.contains(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		WebElement personIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		du.toMouseHover(driver, personIcon);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text() = 'Sign Out']")).click();
		
		driver.quit();
	}

}
