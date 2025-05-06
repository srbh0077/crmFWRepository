package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage 
{
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class = 'dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement orgNameInfo;
	
	@FindBy(id = "mouseArea_Industry")
	private WebElement industryInfo;
	
	@FindBy(id = "mouseArea_Type")
	private WebElement typeInfo;
	
	@FindBy(id = "dtlview_Assigned To")
	private WebElement assignedToInfo;
	
	@FindBy(id = "mouseArea_Phone")
	private WebElement phoneInfo;
	
	@FindBy(id = "mouseArea_Rating")
	private WebElement ratingInfo;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getOrgNameInfo() {
		return orgNameInfo;
	}

	public WebElement getIndustryInfo() {
		return industryInfo;
	}

	public WebElement getTypeInfo() {
		return typeInfo;
	}

	public WebElement getAssignedToInfo() {
		return assignedToInfo;
	}

	public WebElement getPhoneInfo() {
		return phoneInfo;
	}

	public WebElement getRatingInfo() {
		return ratingInfo;
	}
	
	
}








