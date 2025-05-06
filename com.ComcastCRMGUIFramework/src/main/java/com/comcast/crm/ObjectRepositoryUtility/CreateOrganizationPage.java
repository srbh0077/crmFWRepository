package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.DriverUtility;

public class CreateOrganizationPage 
{
	WebDriver driver;
	public CreateOrganizationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name = "industry")
	private WebElement industryDD;
	
	@FindBy(name = "accounttype")
	private WebElement typeDD;
	
	@FindBy(xpath = "//input[@value = 'U']")
	private WebElement assignedToUserChkBox;		
	
	@FindBy(xpath = "//input[@value = 'T']")
	private WebElement assignedToGroupChkBox;	
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedToUserDD;
	
	@FindBy(name = "assigned_group_id")
	private WebElement assignedToGroupDD;
	
	@FindBy(id = "phone")
	private WebElement phoneEdit;
	
	@FindBy(name = "rating")
	private WebElement ratingDD;

	
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}

	public WebElement getAssignedToUserChkBox() {
		return assignedToUserChkBox;
	}

	public WebElement getAssignedToGroupChkBox() {
		return assignedToGroupChkBox;
	}

	public WebElement getAssignedToUserDD() {
		return assignedToUserDD;
	}

	public WebElement getAssignedToGroupDD() {
		return assignedToGroupDD;
	}

	public WebElement getPhoneEdit() {
		return phoneEdit;
	}

	public WebElement getRatingDD() {
		return ratingDD;
	}
	


	public void createOrganizationAction(String name) 
	{
		orgNameEdit.sendKeys(name);
		saveButton.click();
	}
	
	/**
	 * This method is used to create an Organization with provided orgName, industry, type, assignedToGroup, rating
	 * @param name
	 * @param industry
	 * @param type
	 * @param assignedToGroup
	 * @param rating
	 */
	public void createOrganizationAction(String orgName, String industry, String type, String assignedToGroup, String rating)
	{
		orgNameEdit.sendKeys(orgName);
		DriverUtility du = new DriverUtility();
		du.selectOptionByValue(industryDD, industry);
		du.selectOptionByValue(typeDD, type);
		assignedToGroupChkBox.click();
		du.selectOptionByVisibleText(assignedToGroupDD, assignedToGroup);
		du.selectOptionByValue(ratingDD, rating);
		saveButton.click();
	}
	
	public void createOrganizationAction(String name, String phone) 
	{
		orgNameEdit.sendKeys(name);
		phoneEdit.sendKeys(phone);
		saveButton.click();
	}
}
