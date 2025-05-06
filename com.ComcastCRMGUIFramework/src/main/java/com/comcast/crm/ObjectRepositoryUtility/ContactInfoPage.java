package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage 
{
	WebDriver driver;
	public ContactInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class = 'dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement contactNameInfo;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement organizationInfo;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement supportStartDateInfo;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement supportEndDateInfo;
	
	
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getContactNameInfo() {
		return contactNameInfo;
	}

	public WebElement getOrganizationInfo() {
		return organizationInfo;
	}

	public WebElement getSupportStartDateInfo() {
		return supportStartDateInfo;
	}

	public WebElement getSupportEndDateInfo() {
		return supportEndDateInfo;
	}
	
	
	
	
}








