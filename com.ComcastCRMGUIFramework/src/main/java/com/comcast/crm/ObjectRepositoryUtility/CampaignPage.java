package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage
{
	WebDriver driver;
	public CampaignPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title = 'Create Campaign...']")
	private WebElement createCampaignButton;
	
	@FindBy(name = "search_text")
	private WebElement searchForEdit;
	
	@FindBy(id = "bas_searchfield")
	private WebElement inDD;
	
	@FindBy(name = "submit")
	private WebElement searchButton;
	
	@FindBy(xpath = "//input[@class = 'crmbutton small delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//span[@class = 'genHeaderSmall']")
	private WebElement noCampaignText;
	
	
	
	public WebElement getCreateCampaignButton() {
		return createCampaignButton;
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

	public WebElement getNoCampaignText() {
		return noCampaignText;
	}
	
	
	
	
	
	
	
}