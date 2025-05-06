package com.comcast.crm.OrganizationTest;

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

public class CreateOrganizationWIthIndustry_TypeEtcANdValidateTest {

	public static void main(String[] args) throws Throwable 
	{
		PropertyUtility prop = new PropertyUtility();
		DriverUtility du = new DriverUtility();
		ExcelUtility exu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		
		int rndmno = ju.toGetRandomNumber();
		System.out.println(rndmno);
		
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
		
	//	Open the Create Organization page
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		
	//	Fetch Organization name from the Excel File
		exu.getDataFromExcelFile("org", 4, 2);

		String orgCellData = exu.getDataFromExcelFile("org", 4, 2) + rndmno;
		String industryCellData = exu.getDataFromExcelFile("org", 4, 3);
		String typeCellData = exu.getDataFromExcelFile("org", 4, 4);
		String assignedToCellData = exu.getDataFromExcelFile("org", 4, 5);
		String ratingCellData = exu.getDataFromExcelFile("org", 4, 6);
		
	//	Fill the cell with OrgName from Excel file
		driver.findElement(By.name("accountname")).sendKeys(orgCellData);
		
	//	Fill the cell with Industry from Excel file
		WebElement industryDD = driver.findElement(By.name("industry"));
		du.selectOptionByVisibleText(industryDD, industryCellData);
		
	//	Fill the cell with AccountType from Excel file
		WebElement accountDD = driver.findElement(By.name("accounttype"));
		du.selectOptionByVisibleText(accountDD, typeCellData);
		
	//	Fill the cell with AssignedTo(Group) from Excel file
		driver.findElement(By.xpath("//input[@value = 'T']")).click();
		WebElement assignedToDD = driver.findElement(By.name("assigned_group_id"));
		du.selectOptionByVisibleText(assignedToDD, assignedToCellData);

	//	Fill the cell with Rating from Excel file
		WebElement ratingDD = driver.findElement(By.name("rating"));
		du.selectOptionByValue(ratingDD, ratingCellData);
		
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
	//	Verify Expected Industry from Actual Industry
		System.out.println("-----Industry Validation-----");
		String industryInfo = driver.findElement(By.id("dtlview_Industry")).getText();
		if(industryInfo.contains(industryCellData))
			System.out.println(industryCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected Type from Actual Type
		System.out.println("-----Type Validation-----");
		String typeInfo = driver.findElement(By.id("dtlview_Type")).getText();
		if(typeInfo.contains(typeCellData))
			System.out.println(typeCellData + " =======> Information Verified ====> PASS");
		else 
			System.out.println(" =======> Information not Verified ====> FAIL");
		
	//	Verify Expected AssignedTo from Actual AssignedTo
		System.out.println("-----Assigned To Validation-----");
		String assignedInfo = driver.findElement(By.id("dtlview_Assigned To")).getText();
		if(assignedInfo.contains(assignedToCellData))
			System.out.println(assignedToCellData + " =======> Information Verified ====> PASS");
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
