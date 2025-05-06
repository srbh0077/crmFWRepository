package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsInfoPage 
{
	WebDriver driver;
	public ProductsInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class = 'lvtHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Product Name")
	private WebElement productInfo;
	
	@FindBy(id = "dtlview_Sales Start Date")
	private WebElement salesStDateInfo;
	
	@FindBy(id = "dtlview_Product Category")
	private WebElement prodCategoryInfo;
	
	@FindBy(id = "dtlview_Sales End Date")
	private WebElement salesEndDateInfo;
	
	@FindBy(id = "dtlview_Manufacturer")
	private WebElement manufactureInfo;
	
	@FindBy(id = "dtlview_Support Expiry Date")
	private WebElement supportExpDateInfo;
	
	
	
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getProductInfo() {
		return productInfo;
	}

	public WebElement getSalesStDateInfo() {
		return salesStDateInfo;
	}

	public WebElement getProdCategoryInfo() {
		return prodCategoryInfo;
	}

	public WebElement getSalesEndDateInfo() {
		return salesEndDateInfo;
	}

	public WebElement getManufactureInfo() {
		return manufactureInfo;
	}

	public WebElement getSupportExpDateInfo() {
		return supportExpDateInfo;
	}
	
	
	
	

}








