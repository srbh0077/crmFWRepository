package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.DriverUtility;

public class PriceBookPage
{
	WebDriver driver;
	public PriceBookPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title = 'Create Price Book...']")
	private WebElement createPriceBookButton;
	
	@FindBy(name = "search_text")
	private WebElement searchForEdit;
	
	@FindBy(id = "bas_searchfield")
	private WebElement inDD;
	
	@FindBy(name = "submit")
	private WebElement searchButton;
	
	@FindBy(xpath = "//input[@class = 'crmbutton small delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//span[@class = 'genHeaderSmall']")
	private WebElement noPriceBookFoundText;
	
	
	
	
	
	public WebElement getCreatePriceBookButton() {
		return createPriceBookButton;
	}

	public WebElement getSearchForEdit() {
		return searchForEdit;
	}

	public WebElement getInDD() {
		return inDD;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public WebElement getDeleteButton() {
		return deleteButton;
	}
	
	public WebElement getNoPriceBookFoundText() {
		return noPriceBookFoundText;
	}

	
	
	
	
	
	
	public void priceBookNameSearchAction(String nameToSearch, String pbNameOpt) 
	{
		searchForEdit.sendKeys(nameToSearch);
		
		DriverUtility du = new DriverUtility();
		du.selectOptionByVisibleText(inDD, pbNameOpt);
		
		searchButton.click();
	}
	
}