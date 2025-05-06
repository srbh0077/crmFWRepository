package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage 
{
	WebDriver driver;
	public CreateProductPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "productname")
	private WebElement productNameEdit;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name = "sales_start_date")
	private WebElement salesStartDateEdit;
	
	@FindBy(name = "productcategory")
	private WebElement prodCategoryDD;
	
	@FindBy(name = "sales_end_date")
	private WebElement salesEndDateEdit;
	
	@FindBy(name = "manufacturer")
	private WebElement manufactureDD;
	
	@FindBy(name = "expiry_date")
	private WebElement supportExpDateEdit;
	
	
	
	
	
	public WebElement getProductNameEdit() {
		return productNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getSalesStartDateEdit() {
		return salesStartDateEdit;
	}

	public WebElement getProdCategoryDD() {
		return prodCategoryDD;
	}

	public WebElement getSalesEndDateEdit() {
		return salesEndDateEdit;
	}

	public WebElement getManufactureDD() {
		return manufactureDD;
	}

	public WebElement getSupportExpDateEdit() {
		return supportExpDateEdit;
	}
	
	

	
	
	
	
	
	





	
}
