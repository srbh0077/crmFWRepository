package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PriceBookInfoPage 
{
	WebDriver driver;
	public PriceBookInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class = 'lvtHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Price Book Name")
	private WebElement priceBookInfo;
	
	@FindBy(id = "dtlview_Currency")
	private WebElement currencyInfo;
	
	@FindBy(linkText = "Price Books")
	private WebElement priceBookLink;
	
	@FindBy(xpath = "//span[@class = 'genHeaderSmall']")
	private WebElement noPriceBookFoundText;
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getPriceBookInfo() {
		return priceBookInfo;
	}

	public WebElement getCurrencyInfo() {
		return currencyInfo;
	}

	public WebElement getPriceBookLink() {
		return priceBookLink;
	}

	public WebElement getNoPriceBookFoundText() {
		return noPriceBookFoundText;
	}
	
	

}








