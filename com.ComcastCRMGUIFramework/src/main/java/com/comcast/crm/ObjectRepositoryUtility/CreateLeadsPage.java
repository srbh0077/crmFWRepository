package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.DriverUtility;

public class CreateLeadsPage 
{
	WebDriver driver;
	public CreateLeadsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement leadNameEdit;
	
	@FindBy(name = "company")
	private WebElement companyEdit;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name = "leadsource")
	private WebElement leadSourceDD;
	
	@FindBy(name = "industry")
	private WebElement industryDD;
	
	@FindBy(name = "leadstatus")
	private WebElement leadStatusDD;

	@FindBy(name = "rating")
	private WebElement ratingDD;
	
	
	
	
	public WebElement getLeadNameEdit() {
		return leadNameEdit;
	}

	public WebElement getCompanyEdit() {
		return companyEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getLeadSourceDD() {
		return leadSourceDD;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getLeadStatusDD() {
		return leadStatusDD;
	}

	public WebElement getRatingDD() {
		return ratingDD;
	}

	
	/**
	 * 
	 * @param lead
	 * @param company
	 * @param leadSource
	 * @param industry
	 * @param leadStatus
	 * @param rating
	 */
	public void createContactAction(String lead, String company, String leadSource, String industry, String leadStatus, String rating)
	{
		leadNameEdit.sendKeys(lead);
		companyEdit.sendKeys(company);
		
		DriverUtility du = new DriverUtility();
		du.selectOptionByValue(leadSourceDD, leadSource);
		du.selectOptionByValue(industryDD, industry);
		du.selectOptionByValue(leadStatusDD, leadStatus);
		du.selectOptionByValue(ratingDD, rating);
		
		saveButton.click();
	}
}
