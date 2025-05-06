package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsInfoPage 
{
	WebDriver driver;
	public LeadsInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class = 'dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement leadNameInfo;
	
	@FindBy(id = "dtlview_Company")
	private WebElement companyInfo;
	
	@FindBy(id = "dtlview_Lead Source")
	private WebElement leadSourceInfo;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industryInfo;
	
	@FindBy(id = "dtlview_Lead Status")
	private WebElement leadStatusInfo;
	
	@FindBy(id = "dtlview_Rating")
	private WebElement ratingInfo;
	
	
	
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getLeadNameInfo() {
		return leadNameInfo;
	}

	public WebElement getCompanyInfo() {
		return companyInfo;
	}

	public WebElement getLeadSourceInfo() {
		return leadSourceInfo;
	}

	public WebElement getIndustryInfo() {
		return industryInfo;
	}

	public WebElement getLeadStatusInfo() {
		return leadStatusInfo;
	}

	public WebElement getRatingInfo() {
		return ratingInfo;
	}
	
	
	
}








