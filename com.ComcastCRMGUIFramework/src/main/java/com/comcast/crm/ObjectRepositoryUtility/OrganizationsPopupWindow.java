package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPopupWindow 
{
	WebDriver driver;
	public OrganizationsPopupWindow(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[text() = 'Organizations']")
	private WebElement organizationWindow;
	
	@FindBy(id = "search_txt")
	private WebElement orgNameEdit;
	
	@FindBy(name = "search")
	private WebElement searchButton;
	
	@FindBy(id = "1")
	private WebElement orgNameFound;
	
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getOrgNameFound() {
		return orgNameFound;
	}
	
}
