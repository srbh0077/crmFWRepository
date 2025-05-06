package com.comcast.crm.generic.BaseUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.DatabaseUtility.DatabaseUtility;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.PropertyUtility;
import com.comcast.crm.generic.WebdriverUtility.DriverUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;
import com.comcast.crm.generic.WebdriverUtility.ThreadUtilityObject;

public class BaseClass 
{
	public DatabaseUtility dbu = new DatabaseUtility();
	public PropertyUtility prop = new PropertyUtility();
	public DriverUtility du = new DriverUtility();
	public ExcelUtility exu = new ExcelUtility();
	public JavaUtility ju = new JavaUtility();
	
	public WebDriver driver;
	public static WebDriver listenerDriver;
	
	@BeforeSuite(alwaysRun = true)//(groups = {"regression", "integration", "smoke", "system"})
	public void connectToDatabase() throws Throwable
	{
		System.out.println("====== Connect To Database ======");
		dbu.getDBconnection();
	}
	
	
	@BeforeTest(alwaysRun = true)
	public void configBT()
	{
	}
	
	
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void toLauchBrowser(@Optional("chrome") String BROWSER) throws IOException
	{
		System.out.println("====== Launch an Empty Brower ======");
		
		BROWSER = System.getProperty("browser", prop.getDataFromPropertyFile("browser"));
		//BROWSER = prop.getDataFromPropertyFile("browser");	//For Cross-Browser testing only
		if (BROWSER.equalsIgnoreCase("chRome"))
			driver = new ChromeDriver();
		else if (BROWSER.equalsIgnoreCase("FireFox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("EDge")) 
			driver = new EdgeDriver();
		else
			System.out.println("INVALID BROWSER INPUT");
		
		du.toMaximize(driver);
		listenerDriver = driver;
		
		ThreadUtilityObject.setDriver(listenerDriver);
}
	
	
	@BeforeMethod(alwaysRun = true)//(groups = {"regression", "integration", "smoke", "system"})
	public void toLoginTheApplication() throws Throwable
	{
		System.out.println("====== Login to the Application ======");
		
		String URL = System.getProperty("url", prop.getDataFromPropertyFile("url"));
		String UN = System.getProperty("username", prop.getDataFromPropertyFile("username"));
		String PWD = System.getProperty("password", prop.getDataFromPropertyFile("password"));
		
		LoginPage lp = new LoginPage(driver);
		lp.loginAction(URL, UN, PWD);
		
		du.waitForPageToLoad(driver, 15);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void toLogoutApplication()
	{
		System.out.println("====== Logout the Application ======");

		HomePage hp = new HomePage(driver);
		hp.signOutAction();
	}
	
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser()
	{
		System.out.println("====== Close Browser ======");

		if (driver != null) {
            driver.quit();
        }
	}
	
	
	@AfterTest(alwaysRun = true)
	public void configAT()
	{
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void closeDatabaseConnection() throws Throwable
	{
		System.out.println("====== Disconnect from Database ======");
		
		dbu.closeDBconnection();
	}

}
