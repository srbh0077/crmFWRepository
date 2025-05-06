package com.comcast.crm.LeadTest;

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

public class CreateLeadWithAllDropDownsAndValidateTest 
{
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
		
	//	Fetch test data from Excel file
		String leadCellData = exu.getDataFromExcelFile("lead", 1, 2) + rndmno;
		String companyCellData = exu.getDataFromExcelFile("lead", 1, 3);
		String leadSourceCellData = exu.getDataFromExcelFile("lead", 1, 4);
		String industryCellData = exu.getDataFromExcelFile("lead", 1, 5);
		String leadStatusCellData = exu.getDataFromExcelFile("lead", 1, 6);
		String ratingCellData = exu.getDataFromExcelFile("lead", 1, 7);
		
	//	Open the Create Lead page
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Lead...']")).click();
		
	//	Fill the cell with one Lead and Company from Excel file
		driver.findElement(By.name("lastname")).sendKeys(leadCellData);
		driver.findElement(By.name("company")).sendKeys(companyCellData);
		
		WebElement leadSourceDD = driver.findElement(By.name("leadsource"));
		du.selectOptionByValue(leadSourceDD, leadSourceCellData);
		
		WebElement industryDD = driver.findElement(By.name("industry"));
		du.selectOptionByValue(industryDD, industryCellData);
		
		WebElement leadStatusDD = driver.findElement(By.name("leadstatus"));
		du.selectOptionByValue(leadStatusDD, leadStatusCellData);
		
		WebElement RatingDD = driver.findElement(By.name("rating"));
		du.selectOptionByValue(RatingDD, ratingCellData);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Lead Header Data from Expected data
		System.out.println("-----Lead Header Validation-----");
		String headerInfo = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(headerInfo.contains(leadCellData))
			System.out.println(leadCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify actual Lead name from Expected lead name
		System.out.println("-----Lead info Validation-----");
		String leadTextInfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(leadTextInfo.contains(leadCellData))
			System.out.println(leadCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify actual Company name from Expected company name
		System.out.println("-----Company info Validation-----");
		String companyInfo = driver.findElement(By.id("dtlview_Company")).getText();
		if(companyInfo.contains(companyCellData))
			System.out.println(companyCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Industry from Actual Industry
		System.out.println("-----Industry Validation-----");
		String industryInfo = driver.findElement(By.id("dtlview_Industry")).getText();
		if(industryInfo.contains(industryCellData))
			System.out.println(industryCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Lead Source from Actual Lead Source
		System.out.println("-----Lead Source Validation-----");
		String ldsrcInfo = driver.findElement(By.id("dtlview_Lead Source")).getText();
		if(ldsrcInfo.contains(leadSourceCellData))
			System.out.println(leadSourceCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Lead Status from Actual Lead Status
		System.out.println("-----Lead Status Validation-----");
		String ldstsInfo = driver.findElement(By.id("dtlview_Lead Status")).getText();
		if(ldstsInfo.contains(leadStatusCellData))
			System.out.println(leadStatusCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Rating from Actual Rating
		System.out.println("-----Rating Validation-----");
		String ratingInfo = driver.findElement(By.id("dtlview_Rating")).getText();
		if(ratingInfo.contains(ratingCellData))
			System.out.println(ratingCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Signout from Application
		du.toMouseHover(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text() = 'Sign Out']")).click();
			
		driver.quit();
	}

}
