package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.DriverUtility;

public class CreatePriceBookPage 
{
	WebDriver driver;
	public CreatePriceBookPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "bookname")
	private WebElement priceBookNameEdit;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name = "currency_id")
	private WebElement currencyDD;

	
	
	
	
	
	public WebElement getPriceBookNameEdit() {
		return priceBookNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getCurrencyDD() {
		return currencyDD;
	}






	public void createPriceBookAction(String bookName, String currency) 
	{
		priceBookNameEdit.sendKeys(bookName);
		
		DriverUtility du = new DriverUtility();
		du.selectOptionByVisibleText(currencyDD, currency);
		
		saveButton.click();
	}

}
