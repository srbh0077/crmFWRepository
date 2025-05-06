package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPopupWindow 
{
	WebDriver driver;
	public ProductsPopupWindow(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[text() = 'Products']")
	private WebElement productWindow;
	
	@FindBy(id = "search_txt")
	private WebElement prodNameEdit;
	
	@FindBy(name = "search_field")
	private WebElement inDD;

	@FindBy(name = "search")
	private WebElement searchButton;
	
	
	
	
	
	public WebElement getProductWindow() {
		return productWindow;
	}

	public WebElement getProdNameEdit() {
		return prodNameEdit;
	}

	public WebElement getInDD() {
		return inDD;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
}
