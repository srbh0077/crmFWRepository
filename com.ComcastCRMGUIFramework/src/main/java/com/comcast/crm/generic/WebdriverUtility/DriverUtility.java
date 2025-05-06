package com.comcast.crm.generic.WebdriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



/** This Class consists of methods related to Selenium WebDriver
 * @author Amit
 */
public class DriverUtility 
{
	/**
	 * This method is used to Maximize the Window provided driver
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) 
	{
		driver.manage().window().maximize();
	}
	
	
	/**
	 * 
	 * @param validationName
	 * @param element
	 * @param expectedData
	 */
	public void toValidateActualAndExpectedData(String validationName, WebElement element, String expectedData) 
	{
		Reporter.log("-----" + validationName + " Validation-----", true);
		String campInfo = element.getText().trim();
		if(campInfo.contains(expectedData))
			Reporter.log(expectedData + " =======> Information Verified ====> PASS", true);
		else
			Reporter.log(" =======> Information not Verified ====> FAIL", true);
	
		//Assert.assertEquals(campInfo.contains(expectedData), expectedData, validationName + " Validation");
	
	}
	
	
//-----------------------SWITCH TO CHILD WINDOW-------------------------//
	/**
	 * This method is used to switch from Parent window to Child Window provided driver, partialData
	 * @param driver
	 * @param partialData
	 */
	public void switchToChildWindow(WebDriver driver, String partialData) 
	{
		Set<String> allIDs = driver.getWindowHandles();
		for(String ID : allIDs) 
		{
			driver.switchTo().window(ID);
			
			String childTitle = driver.getTitle();
			String childUrl = driver.getCurrentUrl();
			if(childTitle.contains(partialData) || childUrl.contains(partialData))
			{
				break;
			}
		}
	}
	
	
	/**
	 * 
	 * @param driver
	 * @param moduleName
	 * @param partialData
	 */
	public void switchToChildWindow(WebDriver driver, WebElement moduleName, String partialData) 
	{
		Set<String> allIDs = driver.getWindowHandles();
		for(String ID : allIDs) 
		{
			driver.switchTo().window(ID);
			String moduleWindow = moduleName.getText();
			if(moduleWindow.equals(partialData))
			{
				break;
			}
		}
	}
	
//----------------------------SYNCRHRONIZATION--------------------------------//	
	/**
	 * This method is used to wait for complete page to load provided driver, sec
	 * @param driver
	 * @param sec
	 */
	public void waitForPageToLoad(WebDriver driver, long sec)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
		
	/**
	 * This method is used to explicitly wait to check for Alert provided driver, sec
	 * @param driver
	 * @param sec
	 */
	public void alertIsPresent(WebDriver driver, long sec)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForElementIsPresent(WebDriver driver, long sec, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementTobeClickable(WebDriver driver, long sec, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
//---------------------------------TAKE SCREENSHOT------------------------------//	
	/**
	 * 
	 * @param driver
	 * @param fileName
	 * @throws IOException
	 */
	public void toCaptureWindowScreenShot(WebDriver driver, String fileName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + fileName + ".jpg");
		FileHandler.copy(temp, src);
	}
	
	/**
	 * 
	 * @param element
	 * @param fileName
	 * @throws IOException
	 */
	public void toCaptureScreenShotOfElement(WebElement element, String fileName) throws IOException
	{
		File temp = element.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + fileName + ".jpg");
		FileHandler.copy(temp, src);
	}
	
//-------------------------------HANDLE DROPDOWN--------------------------------//	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public boolean toCheckMultiSelectDropdown(WebElement element)
	{
		Select select = new Select(element);
		return select.isMultiple();
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public List<WebElement> getListOfAllOptions(WebElement element)
	{
		Select select = new Select(element);
		return select.getOptions();
	}
	
	/**
	 * 
	 * @param element
	 * @param index
	 */
	public void selectOptionByIndex(WebElement element, int index)
	{
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * 
	 * @param element
	 * @param value
	 */
	public void selectOptionByValue(WebElement element, String value)
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * 
	 * @param element
	 * @param text
	 */
	public void selectOptionByVisibleText(WebElement element, String text)
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * 
	 * @param element
	 * @param index
	 */
	public void deSelectOptionByIndex(WebElement element, int index)
	{
		Select select = new Select(element);
		select.deselectByIndex(index);
	}
	
	/**
	 * 
	 * @param element
	 * @param value
	 */
	public void deSelectOptionByValue(WebElement element, String value)
	{
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	
	/**
	 * 
	 * @param element
	 * @param text
	 */
	public void deDelectOptionByVisibleText(WebElement element, String text)
	{
		Select select = new Select(element);
		select.deselectByVisibleText(text);
	}
	
	/**
	 * 
	 * @param element
	 */
	public void deSelectAllOptions(WebElement element)
	{
		Select select = new Select(element);
		select.deselectAll();
	}
	
//----------------------------HANDLE FRAMES---------------------------------//	
	public void toSwitchFrameUsingID(WebDriver driver, int index) 
	{
		driver.switchTo().frame(index);
	}
	
	public void toSwitchFrameUsingNameOrId(WebDriver driver, String nameOrId) 
	{
		driver.switchTo().frame(nameOrId);
	}
	
	public void toSwitchFrameUsingWebElement(WebDriver driver, WebElement element) 
	{
		driver.switchTo().frame(element);
	}
	
	public void toSwitchToDafault(WebDriver driver) 
	{
		driver.switchTo().defaultContent();
	}
	
	public void toSwitchToParent(WebDriver driver) 
	{
		driver.switchTo().parentFrame();
	}

//-----------------------------ACTION CLASS------------------------------//	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void toClickAnElement(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.click(element).perform();
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void toClickandHold(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.clickAndHold(element).perform();
	}
	
	public void toMouseHover(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void toDragAndDrop(WebDriver driver, WebElement source, WebElement target)
	{
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}
	
	public void toRightClick(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	public void toDoubleClick(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	public void toRelease(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.release(element).perform();
	}
	
	public void toSendKeys(WebDriver driver, WebElement element, String Keys)
	{
		Actions action = new Actions(driver);
		action.click(element).sendKeys(Keys).perform();
	}
	
	public void scrollToElementUsingAction(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.scrollToElement(element).perform();
	}
	
//-----------------------------JAVASCRIPT ALERT POPUP------------------------------------//	
	public void toAcceptAlertPopup(WebDriver driver) 
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void toDismissAlertPopup(WebDriver driver) 
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public String toGetTextOfAlertPopup(WebDriver driver) 
	{
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public void toInsertDataInAlertPopup(WebDriver driver, String value) 
	{
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}
	
//--------------------------JAVASCRIPTEXECUTOR---------------------------------//	
	public void scrollToElementUsingJS(WebDriver driver, boolean tf, WebElement element) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(" + tf + ");", element);
	}
	
	public void scrollByUsingJS(WebDriver driver, int x, int y ) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + ", " + y + ")");
	}
	
	public void clickOnElementUsingJS(WebDriver driver, WebElement element) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void insertValueInElementUsingJS(WebDriver driver, String value, WebElement element) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + value + "';", element);
	}
	
	public void scrollToLastUsingJS(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.ScrollHeight)");
	}
}