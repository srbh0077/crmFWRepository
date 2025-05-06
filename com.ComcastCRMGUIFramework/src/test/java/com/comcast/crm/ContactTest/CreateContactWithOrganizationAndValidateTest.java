package com.comcast.crm.ContactTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class CreateContactWithOrganizationAndValidateTest {

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
		
		WebDriver driver = null;	// local variable
	//	Run-Time Polymorphism achieved here
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
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		
		driver.findElement(By.id("submitButton")).submit();
		
	//	Fetch Data from Excel File
		String contactCellData = exu.getDataFromExcelFile("contact", 7, 2) + rndmno;
		String orgCellData = exu.getDataFromExcelFile("contact", 7, 3) + rndmno;
		
	//	Open the Create Organization page
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		
	//	Fill the cell with one Organization from Excel file
		du.insertValueInElementUsingJS(driver, orgCellData, driver.findElement(By.name("accountname")));
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Header Organization name from Expected Organization name
		System.out.println("-----organization Header Validation-----");
		String orgHeaderInfo = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(orgHeaderInfo.contains(orgCellData))
			System.out.println(orgCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Navigate to Create Contact page
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Contact...']")).click();
		
	//	Fill the cell with a Contact name and Organization name from Lookup window from Excel file
		driver.findElement(By.name("lastname")).sendKeys(contactCellData);
		driver.findElement(By.xpath("//input[@name = 'account_name']/following-sibling::img")).click();
		
	//	Switch to Child Window Perform Action and Come back to Parent
		String parentID = driver.getWindowHandle();
		
		du.switchToChildWindow(driver, "module=Accounts");
			driver.findElement(By.id("search_txt")).sendKeys(orgCellData);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text() = '" + orgCellData + "']")).click();
		
		driver.switchTo().window(parentID);
		
	//	Save Contact Data
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Expected Contact header with Actual Contact name
		System.out.println("-----Contact Header Validation-----");
		String conHeaderInfo = driver.findElement(By.xpath("//span[@class= 'dvHeaderText']")).getText();
		if(conHeaderInfo.contains(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Contact lastname with Actual Contact name
		System.out.println("-----Contact lastname Validation-----");
		String contactInfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(contactInfo.equals(contactCellData))
			System.out.println(contactCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		du.toMouseHover(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text() = 'Sign Out']")).click();
		
		driver.quit();
	}
}
